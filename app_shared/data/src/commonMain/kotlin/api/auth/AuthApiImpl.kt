package api.auth

import api.BaseApi
import com.nowiwr01p.model.api.request.SignInRequest
import com.nowiwr01p.model.api.request.SignUpRequest
import com.nowiwr01p.model.model.User
import org.kodein.di.DI

class AuthApiImpl(kodein: DI): BaseApi(kodein), AuthApi {

    override suspend fun signUp(request: SignUpRequest): User {
        return postHttp(route = "v1/auth/signUp")
    }

    override suspend fun signIn(request: SignInRequest): User {
        return postHttp(route = "v1/auth/signIn")
    }
}