package nowiwr01p.daily.doctor.server.data.di

import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import nowiwr01p.daily.doctor.database.domain.repository.auth.DatabaseAuthRepository
import nowiwr01p.daily.doctor.database.domain.repository.verification.DatabaseVerificationRepository
import nowiwr01p.daily.doctor.server.data.repository.auth.ServerAuthRepositoryImpl
import nowiwr01p.daily.doctor.server.data.repository.verification.ServerVerificationRepositoryImpl
import nowiwr01p.daily.doctor.server.domain.repository.auth.ServerAuthRepository
import nowiwr01p.daily.doctor.server.domain.repository.verification.ServerVerificationRepository
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val moduleServerRepository = DI.Module("ServerRepositoryModule") {
    /**
     * AUTH
     */
    bindProvider<ServerAuthRepository> {
        ServerAuthRepositoryImpl(
            dispatchers = instance<AppDispatchers>(),
            authRepository = instance<DatabaseAuthRepository>(),
            verificationRepository = instance<DatabaseVerificationRepository>()
        )
    }
    /**
     * VERIFICATION
     */
    bindProvider<ServerVerificationRepository> {
        ServerVerificationRepositoryImpl(
            dispatchers = instance<AppDispatchers>(),
            verificationRepository = instance<DatabaseVerificationRepository>()
        )
    }
}