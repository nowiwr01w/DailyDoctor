package nowiwr01p.daily.doctor.server.data.usecase

import nowiwr01p.daily.doctor.server.domain.repository.user.UserRepositoryServer
import nowiwr01p.daily.doctor.server.domain.usecase.GetServerUserByIdUseCase

class GetServerUserByIdUseCaseImpl(
    private val repository: UserRepositoryServer
): GetServerUserByIdUseCase {

    override suspend fun execute(input: String) = repository.getUserById(input)
}