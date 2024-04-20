package nowiwr01p.daily.doctor.server.data.usecase

import nowiwr01p.daily.doctor.server.domain.repository.UserRepositoryServer
import nowiwr01p.daily.doctor.server.domain.usecase.GetServerUserByIdUseCase

class GetServerUserByIdUseCaseImpl(
    private val repository: UserRepositoryServer
): GetServerUserByIdUseCase {

    override suspend fun execute(input: Int) = repository.getUserById(input)
}