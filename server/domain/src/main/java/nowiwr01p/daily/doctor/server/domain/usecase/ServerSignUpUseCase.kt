package nowiwr01p.daily.doctor.server.domain.usecase

import com.nowiwr01p.model.api.request.auth.SignUpRequest
import com.nowiwr01p.model.api.response.auth.SignUpResponse
import com.nowiwr01p.model.usecase.UseCase

interface ServerSignUpUseCase: UseCase<SignUpRequest, SignUpResponse>