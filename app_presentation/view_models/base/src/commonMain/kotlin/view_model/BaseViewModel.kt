package view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import androidx.compose.runtime.State as ComposeState

abstract class BaseViewModel<Event: BaseEvent, State: BaseState, Effect: BaseEffect>(
    private val coroutineScope: CoroutineScope // each screen scope
) {
    /**
     * STATE
     */
    private val _viewState: MutableState<State> by lazy { mutableStateOf(initialState) }
    val viewState: ComposeState<State> by lazy { _viewState }

    private val initialState: State by lazy { setInitialState() }
    abstract fun setInitialState(): State

    protected fun setState(reducer: State.() -> State) {
        _viewState.value = viewState.value.reducer()
    }
    /**
     * EVENT
     */
    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()

    private fun subscribeToEvents() = coroutineScope.launch(Dispatchers.Default) {
        _event.collect { event -> handleEvents(event) }
    }
    abstract fun handleEvents(event: Event)

    fun setEvent(event: Event) = coroutineScope.launch(Dispatchers.Default) {
        _event.emit(event)
    }
    init {
        subscribeToEvents()
    }
    /**
     * EFFECT
     */
    private val _effect = Channel<Effect>()
    val effect = _effect.receiveAsFlow()

    protected fun setEffect(builder: () -> Effect) = coroutineScope.launch(Dispatchers.Default) {
        _effect.send(builder())
    }
    /**
     * EXECUTE CODE IN ANOTHER THREAD
     */
    fun BaseViewModel<*, *, *>.hide(
        handler: CoroutineExceptionHandler? = null,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return if (handler == null) {
            coroutineScope.launch(Dispatchers.Default) { block() }
        } else {
            coroutineScope.launch(Dispatchers.Default + handler) { block() }
        }
    }
}