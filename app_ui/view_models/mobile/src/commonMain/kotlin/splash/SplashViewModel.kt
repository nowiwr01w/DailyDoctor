package splash

import com.nowiwr01p.model.coroutines.app_scope.AppScope
import com.nowiwr01p.model.usecase.execute
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import nowiwr01p.daily.doctor.local_database.domain.usecase.user.GetLocalUserUseCase
import pro.respawn.flowmvi.api.PipelineContext
import splash.Effect.NavigateToHome
import splash.Effect.NavigateToOnboarding
import splash.Effect.ShowSelectLanguageDialog
import splash.Event.InitAppDataAfterLanguageSet
import usecase.InitAppDataUseCase
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
        setEffect(ShowSelectLanguageDialog)
    }

    override suspend fun Ctx.handleEvents(event: Event) {
        when (event) {
            is InitAppDataAfterLanguageSet -> initAppData()
        }
    }

    /**
     * APP DATA
     */
    private fun Ctx.initAppData() = appScope.scope.launch {
        setState { copy(showProgress = true) }
        initAppDataUseCase.execute()
        chooseNavigationDestination()
    }

    /**
     * NAVIGATION
     */
    private suspend fun Ctx.chooseNavigationDestination() {
        val effect = when {
            getLocalUserUseCase.execute() != null -> NavigateToHome
            else -> NavigateToOnboarding
        }
        delay(750) // TODO: Remove when your server will not be a "localhost"
        setEffect(effect)
    }
}
