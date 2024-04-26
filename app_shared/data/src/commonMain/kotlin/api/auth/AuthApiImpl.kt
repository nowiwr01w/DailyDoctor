package api.auth

import api.BaseApi
import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.api.response.auth.SignUpResponse
import com.nowiwr01p.model.api.route.AuthRoutes.SingInRoute
import com.nowiwr01p.model.api.route.AuthRoutes.SingUpRoute
import com.nowiwr01p.model.model.User
import org.kodein.di.DI

class AuthApiImpl(kodein: DI): BaseApi(kodein), AuthApi {

    override suspend fun signIn(request: SignInRequest) = postHttp<User>(
        route = SingInRoute.route,
        requestBody = request
    )

    override suspend fun signUp(request: SignUpRequest) = postHttp<SignUpResponse>(
        route = SingUpRoute.route,
        requestBody = request
    )
}