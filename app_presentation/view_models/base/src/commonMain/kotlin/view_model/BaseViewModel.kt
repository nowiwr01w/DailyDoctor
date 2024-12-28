package view_model

import androidx.compose.runtime.Composable
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pro.respawn.flowmvi.api.ActionShareBehavior
import pro.respawn.flowmvi.api.Container
import pro.respawn.flowmvi.api.PipelineContext
import pro.respawn.flowmvi.api.StateStrategy
import pro.respawn.flowmvi.compose.dsl.subscribe
import pro.respawn.flowmvi.dsl.lazyStore
import pro.respawn.flowmvi.plugins.enableLogging
import pro.respawn.flowmvi.plugins.init
import pro.respawn.flowmvi.plugins.recover
import pro.respawn.flowmvi.plugins.reduce

abstract class BaseViewModel<State: BaseState, Event: BaseEvent, Effect: BaseEffect>(
    initialValue: State,
): Container<State, Event, Effect>, KoinComponent, InstanceKeeper.Instance {
    protected val dispatchers by inject<AppDispatchers>()
    private var isInitBlockWasExecuted = false
    protected val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    protected open suspend fun PipelineContext<State, Event, Effect>.init() {}
    protected open suspend fun PipelineContext<State, Event, Effect>.handleEvents(event: Event) {}

    override val store by lazyStore<State, Event, Effect>(
        initial = initialValue,
        scope = scope
    ) {
        configure {
            name = this::class.simpleName
            actionShareBehavior = ActionShareBehavior.Distribute()
            coroutineContext = dispatchers.io
            debuggable = true
            parallelIntents = true
            stateStrategy = StateStrategy.Atomic()
        }

        enableLogging(tag = "Zhopa")

        init {
            if (!isInitBlockWasExecuted) {
                init()
                isInitBlockWasExecuted = true
            }
        }

        recover { error ->
            handleErrors(error)
            null
        }

        reduce { event ->
            handleEvents(event)
        }
    }

    fun startStore() = store.start(scope)

    override fun onDestroy() {
        scope.cancel()
    }

    fun setEvent(event: Event) {
        store.intent(event)
    }

    @Composable
    fun getState(handleEffects: suspend (Effect) -> Unit): State {
        val state = store.subscribe { effect ->
            handleEffects(effect)
        }
        return state.value
    }

    protected suspend fun PipelineContext<State, Event, Effect>.setState(
        reducer: suspend State.() -> State
    ) {
        updateState(reducer)
    }

    protected suspend fun PipelineContext<State, Event, Effect>.setEffect(effect: Effect) {
        action(effect)
    }

    protected open fun PipelineContext<State, Event, Effect>.handleErrors(error: Exception) {}

    protected fun io(block: suspend CoroutineScope.() -> Unit) = scope.launch(
        context = dispatchers.io,
        block = block
    )
}
