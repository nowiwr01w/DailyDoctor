package api.auth

import com.nowiwr01p.model.api.errors.auth.AuthApiError
import com.nowiwr01p.model.api.errors.auth.AuthApiError.SignInApiError
import com.nowiwr01p.model.api.errors.auth.AuthApiError.SignUpApiError
import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.api.route.AuthRoutes.SingInRoute
import com.nowiwr01p.model.api.route.AuthRoutes.SingUpRoute
import nowiwr01p.daily.doctor.base_api_client.api.ApiClientSettings.AppApiClientSettings
import nowiwr01p.daily.doctor.base_api_client.api.BaseApi

class AuthApiImpl: BaseApi<AuthApiError>(AppApiClientSettings), AuthApi {
    /**
     * SIGN IN
     */
    override suspend fun signIn(request: SignInRequest) = postHttp<TokenResponse, SignInApiError>(
        route = SingInRoute,
        requestBodyString = request.encodeDataToString(),
        error = { message -> SignInApiError(message) }
    )

    /**
     * SIGN UP
     */
    override suspend fun signUp(request: SignUpRequest) = postHttp<TokenResponse, SignUpApiError>(
        route = SingUpRoute,
        requestBodyString = request.encodeDataToString(),
        error = { message -> SignUpApiError(message) }
    )
}