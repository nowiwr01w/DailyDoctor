package nowiwr01p.daily.doctor.server.routes.brand_config

import com.nowiwr01p.model.api.route.BrantConfigRoutes.GetBrandConfigRoute
import com.nowiwr01p.model.extensions.runCatchingApp
import com.nowiwr01p.model.model.app_config.config.BrandConfigType
import io.ktor.http.HttpStatusCode
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import nowiwr01p.daily.doctor.server.domain.usecase.brand_config.ServerGetBrandConfigUseCase
import nowiwr01p.daily.doctor.server.routes.BaseRouting

class BrandConfigRouting(
    private val serverGetBrandConfigUseCase: ServerGetBrandConfigUseCase
): BaseRouting() {

    fun getBrandConfig(route: Route) = route.get(GetBrandConfigRoute.route) {
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
            serverGetBrandConfigUseCase.execute(brandConfigType)
        }.onSuccess { brandConfig ->
            respondWithSuccessModel(brandConfig)
        }.onFailure { error ->
            sendInternalError(error.message)
        }
    }
}