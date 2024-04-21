package api.auth

import api.BaseApi
import com.nowiwr01p.model.api.request.SignInRequest
import com.nowiwr01p.model.api.request.SignUpRequest
import com.nowiwr01p.model.model.User
import org.kodein.di.DI

class AuthApiImpl(kodein: DI): BaseApi(kodein), AuthApi {

    override suspend fun signIn(request: SignInRequest) = postHttp<User>(
        route = "v1/auth/signIn",
        requestBody = request
    )

    override suspend fun signUp(request: SignUpRequest) = postHttp<User>(
        route = "v1/auth/signUp",
        requestBody = request
    )
}