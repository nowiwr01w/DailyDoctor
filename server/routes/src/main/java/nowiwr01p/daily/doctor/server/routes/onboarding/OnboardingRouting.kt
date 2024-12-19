package nowiwr01p.daily.doctor.server.routes.onboarding

import com.nowiwr01p.model.api.route.OnboardingRoutes.GetOnboardingDataRoute
import com.nowiwr01p.model.extensions.runCatchingApp
import com.nowiwr01p.model.model.app_config.config.BrandConfigType
import io.ktor.http.HttpStatusCode
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import nowiwr01p.daily.doctor.server.domain.usecase.onboarding.ServerGetOnboardingDataUseCase
import nowiwr01p.daily.doctor.server.routes.BaseRouting

class OnboardingRouting(
    private val serverGetOnboardingDataUseCase: ServerGetOnboardingDataUseCase
): BaseRouting() {

    fun getOnboardingData(route: Route) = route.get(GetOnboardingDataRoute.route) {
        val brandConfigType = getParameter<BrandConfigType>(
            name = "type",
            paramAsType = { stringParam ->
                BrandConfigType.entries.find { stringParam == it.type }
            }
        ) ?: return@get
        runCatchingApp {
            serverGetOnboardingDataUseCase.execute(brandConfigType)
        }.onSuccess { onboardingData ->
            respondWithSuccessModel(onboardingData)
        }.onFailure { error ->
            sendInternalError(error.message)
        }
    }
}