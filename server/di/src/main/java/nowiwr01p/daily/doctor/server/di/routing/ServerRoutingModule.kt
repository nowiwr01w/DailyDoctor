package nowiwr01p.daily.doctor.server.di.routing

import nowiwr01p.daily.doctor.server.domain.usecase.auth.ServerSignInUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.auth.ServerSignUpUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.verification.ServerCheckVerificationCodeUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.verification.ServerSendVerificationCodeUseCase
import nowiwr01p.daily.doctor.server.routes.auth.AuthRouting
import nowiwr01p.daily.doctor.server.routes.verification.VerificationRouting
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

internal val moduleServerRouting = DI.Module("ServerRoutingModule") {
    /**
     * AUTH
     */
    bindProvider {
        AuthRouting(
            serverSignInUseCase = instance<ServerSignInUseCase>(),
            serverSignUpUseCase = instance<ServerSignUpUseCase>()
        )
    }
    /**
     * VERIFICATION
     */
    bindProvider {
        VerificationRouting(
            serverSendVerificationCodeUseCase = instance<ServerSendVerificationCodeUseCase>(),
            serverCheckVerificationCodeUseCase = instance<ServerCheckVerificationCodeUseCase>()
        )
    }
}