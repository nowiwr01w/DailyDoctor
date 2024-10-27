package user.usecase

import user.repository.LocalUserRepository

class GetLocalUserUseCaseImpl(
    private val repository: LocalUserRepository
): GetLocalUserUseCase {

    override suspend fun execute(input: Unit) = repository.getUser()
}