package usecase.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.usecase.UseCase

interface AppCheckVerificationCodeUseCase: UseCase<CheckVerificationCodeRequest, TokenResponse>