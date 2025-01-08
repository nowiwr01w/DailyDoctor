package nowiwr01p.daily.doctor.server.routes.brand_config

import com.nowiwr01p.model.api.route.BrantConfigRoutes.GetBrandConfigRoute
import com.nowiwr01p.model.extensions.runCatchingApp
import com.nowiwr01p.model.usecase.execute
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
        val publicKey = getParameter<String>(
            name = "key",
            paramAsType = { stringParam -> stringParam }
        ) ?: return@get
        encryptionServer.setOtherSidePublicKey(publicKey)
        runCatchingApp {
            serverGetBrandConfigUseCase.execute()
        }.onSuccess { brandConfig ->
            respondWithSuccessModel(brandConfig)
        }.onFailure { error ->
            sendInternalError(error.message)
        }
    }
}