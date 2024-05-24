package nowiwr01p.daily.doctor.server.di.routing

import kotlinx.serialization.json.Json
import nowiwr01p.daily.doctor.server.domain.usecase.auth.ServerSignInUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.auth.ServerSignUpUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerChangePinCodeUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerCheckPinCodeUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerCreatePinCodeUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.pin.ServerDeletePinCodeUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.verification.ServerCheckVerificationCodeUseCase
import nowiwr01p.daily.doctor.server.domain.usecase.verification.ServerSendVerificationCodeUseCase
import nowiwr01p.daily.doctor.server.routes.auth.AuthRouting
import nowiwr01p.daily.doctor.server.routes.pin_code.PinCodeRouting
import nowiwr01p.daily.doctor.server.routes.verification.VerificationRouting
import org.koin.dsl.module

internal val moduleServerRouting = module {
    /**
     * JSON
     */
    factory {
        Json {
            isLenient = true
            prettyPrint = true
            ignoreUnknownKeys = true
        }
    }
    /**
     * AUTH
     */
    factory {
        AuthRouting(
            serverSignInUseCase = get<ServerSignInUseCase>(),
            serverSignUpUseCase = get<ServerSignUpUseCase>()
        )
    }
    /**
     * VERIFICATION
     */
    factory {
        VerificationRouting(
            serverSendVerificationCodeUseCase = get<ServerSendVerificationCodeUseCase>(),
            serverCheckVerificationCodeUseCase = get<ServerCheckVerificationCodeUseCase>()
        )
    }
    /**
     * PIN
     */
    factory {
        PinCodeRouting(
            changePinCodeUseCase = get<ServerChangePinCodeUseCase>(),
            createPinCodeUseCase = get<ServerCreatePinCodeUseCase>(),
            deletePinCodeUseCase = get<ServerDeletePinCodeUseCase>(),
            checkPinCodeUseCase = get<ServerCheckPinCodeUseCase>()
        )
    }
}