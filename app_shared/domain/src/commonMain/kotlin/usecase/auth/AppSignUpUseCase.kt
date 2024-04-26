package usecase.auth

import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.api.response.auth.SignUpResponse
import com.nowiwr01p.model.usecase.UseCase

interface AppSignUpUseCase: UseCase<SignUpRequest, SignUpResponse>