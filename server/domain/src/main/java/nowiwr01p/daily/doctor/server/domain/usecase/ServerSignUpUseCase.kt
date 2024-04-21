package nowiwr01p.daily.doctor.server.domain.usecase

import com.nowiwr01p.model.api.request.SignUpRequest
import com.nowiwr01p.model.model.User
import com.nowiwr01p.model.usecase.UseCase

interface ServerSignUpUseCase: UseCase<SignUpRequest, User>