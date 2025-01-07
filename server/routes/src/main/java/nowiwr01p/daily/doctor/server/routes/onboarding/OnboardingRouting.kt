package nowiwr01p.daily.doctor.server.routes.onboarding

import com.nowiwr01p.model.api.route.OnboardingRoutes.GetOnboardingDataRoute
import com.nowiwr01p.model.extensions.runCatchingApp
import com.nowiwr01p.model.resources.language.Language
import com.nowiwr01p.model.resources.language.appLanguages
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import nowiwr01p.daily.doctor.server.domain.usecase.onboarding.ServerGetOnboardingDataUseCase
import nowiwr01p.daily.doctor.server.routes.BaseRouting

class OnboardingRouting(
    private val serverGetOnboardingDataUseCase: ServerGetOnboardingDataUseCase
): BaseRouting() {

    fun getOnboardingData(route: Route) = route.get(GetOnboardingDataRoute.route) {
        val language = getParameter<Language>( // TODO: Move to BaseRouting
            name = "lang",
            paramAsType = { stringParam ->
                appLanguages.find { it.code == stringParam }
            }
        ) ?: return@get
        runCatchingApp {
            serverGetOnboardingDataUseCase.execute(language)
        }.onSuccess { onboardingData ->
            respondWithSuccessModel(onboardingData)
        }.onFailure { error ->
            sendInternalError(error.message)
        }
    }
}