package nowiwr01p.daily.doctor.server.data.di

import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import nowiwr01p.daily.doctor.database.repository.user.UserRepositoryDatabase
import nowiwr01p.daily.doctor.server.data.repository.UserRepositoryServerImpl
import nowiwr01p.daily.doctor.server.domain.repository.UserRepositoryServer
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val moduleServerRepository = DI.Module("ServerUseModule") {
    /**
     * USER
     */
    bindProvider<UserRepositoryServer> {
        UserRepositoryServerImpl(
            dispatchers = instance<AppDispatchers>(),
            userRepositoryDatabase = instance<UserRepositoryDatabase>()
        )
    }
}