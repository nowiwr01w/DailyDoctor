package api.auth

import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.api.route.AuthRoutes.SingInRoute
import com.nowiwr01p.model.api.route.AuthRoutes.SingUpRoute
import nowiwr01p.daily.doctor.base_api_client.api.ApiClientSettings.AppApiClientSettings
import nowiwr01p.daily.doctor.base_api_client.api.BaseApi

class AuthApiImpl: BaseApi(AppApiClientSettings), AuthApi {

    override suspend fun signIn(request: SignInRequest) = postHttp<TokenResponse>(
        route = SingInRoute,
        requestBody = request
    )

    override suspend fun signUp(request: SignUpRequest) = postHttp<TokenResponse>(
        route = SingUpRoute,
        requestBody = request
    )
}