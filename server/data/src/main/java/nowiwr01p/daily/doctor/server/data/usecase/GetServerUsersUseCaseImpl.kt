package nowiwr01p.daily.doctor.server.data.usecase

import nowiwr01p.daily.doctor.server.domain.repository.user.UserRepositoryServer
import nowiwr01p.daily.doctor.server.domain.usecase.GetServerUsersUseCase

class GetServerUsersUseCaseImpl(
    private val repository: UserRepositoryServer
): GetServerUsersUseCase {

    override suspend fun execute(input: Unit) = repository.getUsers()
}