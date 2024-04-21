package nowiwr01p.daily.doctor.server.main.di

import nowiwr01p.daily.doctor.server.domain.usecase.ServerSignUpUseCase
import nowiwr01p.daily.doctor.server.main.routing.RoutingAuth
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val moduleServerUserRouting = DI.Module("ServerUserRoutingModule") {
    /**
     * AUTH
     */
    bindProvider {
        RoutingAuth(
            serverSignUpUseCase = instance<ServerSignUpUseCase>()
        )
    }
}