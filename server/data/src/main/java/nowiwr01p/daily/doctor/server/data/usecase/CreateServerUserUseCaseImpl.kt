package nowiwr01p.daily.doctor.server.data.usecase

import com.nowiwr01p.model.api.request.CreateUserRequest
import com.nowiwr01p.model.model.User
import nowiwr01p.daily.doctor.server.domain.repository.user.UserRepositoryServer
import nowiwr01p.daily.doctor.server.domain.usecase.CreateServerUserUseCase

class CreateServerUserUseCaseImpl(
    private val repository: UserRepositoryServer
): CreateServerUserUseCase {

    override suspend fun execute(input: CreateUserRequest) = repository.createUser(input)
}