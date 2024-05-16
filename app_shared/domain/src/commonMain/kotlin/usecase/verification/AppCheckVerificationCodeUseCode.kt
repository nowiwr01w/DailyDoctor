package usecase.verification

import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.api.response.verification.AuthTokenResponse
import com.nowiwr01p.model.usecase.UseCase

interface AppCheckVerificationCodeUseCode: UseCase<CheckVerificationCodeRequest, Unit>