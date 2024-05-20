package usecase.verification

import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import com.nowiwr01p.model.api.response.token.TokenResponse
import com.nowiwr01p.model.usecase.UseCase

interface AppSendVerificationCodeUseCase: UseCase<SendVerificationCodeRequest, TokenResponse>