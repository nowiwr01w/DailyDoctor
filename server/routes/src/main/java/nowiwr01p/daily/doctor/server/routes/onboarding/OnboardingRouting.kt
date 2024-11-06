package nowiwr01p.daily.doctor.server.routes.onboarding

import com.nowiwr01p.model.api.route.BrantConfigRoutes.GetBrandConfigRoute
import com.nowiwr01p.model.api.route.OnboardingRoutes
import com.nowiwr01p.model.api.route.OnboardingRoutes.GetOnboardingDataRoute
import com.nowiwr01p.model.extensions.runCatchingApp
import com.nowiwr01p.model.model.app_config.config.BrandConfigType
import com.nowiwr01p.model.usecase.execute
import io.ktor.http.HttpStatusCode
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import nowiwr01p.daily.doctor.server.domain.usecase.onboarding.ServerGetOnboardingDataUseCase
import nowiwr01p.daily.doctor.server.routes.BaseRouting

class OnboardingRouting(
    private val serverGetOnboardingDataUseCase: ServerGetOnboardingDataUseCase
): BaseRouting() {

    fun getOnboardingData(route: Route) = route.get(GetOnboardingDataRoute.route) {
        val type = call.queryParameters["type"] ?: run {
            sendNoRequestError<BrandConfigType>()
            return@get
        }
        val brandConfigType = BrandConfigType.entries.find { it.type == type } ?: run {
            sendRoutingError(
                code = HttpStatusCode.BadRequest,
                message = "Wrong [type] parameter at ${GetBrandConfigRoute.route} route"
            )
            return@get
        }
        runCatchingApp {
            serverGetOnboardingDataUseCase.execute()
        }.onSuccess { onboardingData ->
            respondWithSuccessModel(onboardingData)
        }.onFailure { error ->
            sendInternalError(error.message)
        }
    }
}