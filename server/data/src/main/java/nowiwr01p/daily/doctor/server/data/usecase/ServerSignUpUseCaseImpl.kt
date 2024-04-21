package nowiwr01p.daily.doctor.server.data.usecase

import com.nowiwr01p.model.api.request.SignUpRequest
import nowiwr01p.daily.doctor.server.domain.repository.user.UserRepositoryServer
import nowiwr01p.daily.doctor.server.domain.usecase.ServerSignUpUseCase

class ServerSignUpUseCaseImpl(
    private val repository: UserRepositoryServer
): ServerSignUpUseCase {

    override suspend fun execute(input: SignUpRequest) = repository.signUp(input)
}