package nowiwr01p.daily.doctor.server.routes.brand_config

import com.nowiwr01p.model.api.route.BrantConfigRoutes.GetBrandConfigRoute
import com.nowiwr01p.model.extensions.runCatchingApp
import com.nowiwr01p.model.model.app_config.config.BrandConfigType
import io.ktor.http.HttpStatusCode
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import nowiwr01p.daily.doctor.encryption.server.EncryptionServer
import nowiwr01p.daily.doctor.server.domain.usecase.brand_config.ServerGetBrandConfigUseCase
import nowiwr01p.daily.doctor.server.routes.BaseRouting

class BrandConfigRouting(
    private val serverGetBrandConfigUseCase: ServerGetBrandConfigUseCase,
    private val encryptionServer: EncryptionServer
): BaseRouting() {

    fun getBrandConfig(route: Route) = route.get(GetBrandConfigRoute.route) {
        encryptionServer.initPublicKey()
        val brandConfigType = getParameter<BrandConfigType>(
            name = "type",
            paramAsType = { stringParam ->
                BrandConfigType.entries.find { stringParam == it.type }
            }
        ) ?: return@get
        val publicKey = getParameter<String>(
            name = "key",
            paramAsType = { stringParam -> stringParam }
        ) ?: return@get
        encryptionServer.setOtherSidePublicKey(publicKey)
        runCatchingApp {
            serverGetBrandConfigUseCase.execute(brandConfigType)
        }.onSuccess { brandConfig ->
            respondWithSuccessModel(brandConfig)
        }.onFailure { error ->
            sendInternalError(error.message)
        }
    }
}