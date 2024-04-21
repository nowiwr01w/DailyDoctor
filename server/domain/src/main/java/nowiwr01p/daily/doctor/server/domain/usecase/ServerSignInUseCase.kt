package nowiwr01p.daily.doctor.server.domain.usecase

import com.nowiwr01p.model.api.request.SignInRequest
import com.nowiwr01p.model.model.User
import com.nowiwr01p.model.usecase.UseCase

interface ServerSignInUseCase: UseCase<SignInRequest, User>