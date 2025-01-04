package splash

import com.nowiwr01p.model.coroutines.app_scope.AppScope
import com.nowiwr01p.model.usecase.execute
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import pro.respawn.flowmvi.api.PipelineContext
import splash.Effect.NavigateToHome
import splash.Effect.NavigateToOnboarding
import splash.Effect.ShowSelectLanguageDialog
import splash.Event.InitAppDataAfterLanguageSet
import splash.data.SplashAnimationState
import usecase.InitAppDataUseCase
import user.usecase.GetLocalUserUseCase
import view_model.BaseViewModel

private typealias Ctx = PipelineContext<State, Event, Effect>

class SplashViewModel(
    private val appScope: AppScope,
    private val getLocalUserUseCase: GetLocalUserUseCase,
    private val initAppDataUseCase: InitAppDataUseCase
): BaseViewModel<State, Event, Effect>(initialValue = State()) {
    /**
     * INIT
     */
    override suspend fun Ctx.init() {
        showSelectLanguageDialog()
    }

    private fun Ctx.initAppData() {
        loadAppData()
        startTimer()
    }

    override suspend fun Ctx.handleEvents(event: Event) {
        when (event) {
            is InitAppDataAfterLanguageSet -> initAppData()
        }
    }

    /**
     * APP DATA
     */
    private fun loadAppData() = appScope.scope.launch {
        initAppDataUseCase.execute()
    }

    /**
     * ANIMATION
     */
    private fun Ctx.startTimer() = io {
        val animationDuration = SplashAnimationState.entries
            .last()
            .showUntilAtMillis
        (0..animationDuration step 100).asSequence()
            .asFlow()
            .onEach { millis ->
                setAnimationState(millis)
                delay(100)
            }
            .onCompletion {
                chooseNavigationDestination()
            }
            .collect()
    }

    private suspend fun Ctx.setAnimationState(millis: Int) = setState {
        val animationState = SplashAnimationState.entries.first { item ->
            millis <= item.showUntilAtMillis
        }
        copy(animationState = animationState)
    }

    /**
     * NAVIGATION
     */
    private suspend fun Ctx.chooseNavigationDestination() {
        val effect = when {
            getLocalUserUseCase.execute() != null -> NavigateToHome
            else -> NavigateToOnboarding
        }
        delay(2500) // hide SelectLanguageDialog animation + extra delay
        setEffect(effect)
    }

    /**
     * SELECT LANGUAGE DIALOG
     */
    private suspend fun Ctx.showSelectLanguageDialog() = setEffect(ShowSelectLanguageDialog)
}
