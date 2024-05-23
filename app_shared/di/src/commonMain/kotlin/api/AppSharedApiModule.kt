package api

import api.auth.AuthApi
import api.auth.AuthApiImpl
import api.pin.PinApi
import api.pin.PinApiImpl
import api.verification.VerificationApi
import api.verification.VerificationApiImpl
import org.koin.dsl.module

internal val moduleAppSharedApi = module {
    /**
     * AUTH
     */
    single<AuthApi> {
        AuthApiImpl()
    }
    /**
     * VERIFICATION
     */
    single<VerificationApi> {
        VerificationApiImpl()
    }
    /**
     * PIN
     */
    single<PinApi> {
        PinApiImpl()
    }
}