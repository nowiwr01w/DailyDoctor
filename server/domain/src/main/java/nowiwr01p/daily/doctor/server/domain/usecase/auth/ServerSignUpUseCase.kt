package nowiwr01p.daily.doctor.server.domain.usecase.auth

import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.usecase.UseCase

interface ServerSignUpUseCase: UseCase<SignUpRequest, TokenResponse>