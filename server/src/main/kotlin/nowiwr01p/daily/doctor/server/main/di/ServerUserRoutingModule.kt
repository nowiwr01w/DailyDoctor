package nowiwr01p.daily.doctor.server.main.di

import nowiwr01p.daily.doctor.server.domain.usecase.GetServerUserByIdUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.GetServerUsersUseCase
import nowiwr01p.daily.doctor.server.main.routing.RoutingUser
import nowiwr01p.daily.doctor.server.main.routing.RoutingUsers
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val moduleServerUserRouting = DI.Module("ServerUserRoutingModule") {
    /**
     * USER BY ID
     */
    bindProvider {
        RoutingUser(
            getServerUserByIdUseCase = instance<GetServerUserByIdUseCase>()
        )
    }
    /**
     * USERS
     */
    bindProvider {
        RoutingUsers(
            getServerUsersUseCase = instance<GetServerUsersUseCase>()
        )
    }
}