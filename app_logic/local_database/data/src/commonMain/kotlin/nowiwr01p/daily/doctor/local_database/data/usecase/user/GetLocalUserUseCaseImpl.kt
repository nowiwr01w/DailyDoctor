package nowiwr01p.daily.doctor.local_database.data.usecase.user

import nowiwr01p.daily.doctor.local_database.domain.repository.user.LocalUserRepository
import nowiwr01p.daily.doctor.local_database.domain.usecase.user.GetLocalUserUseCase

class GetLocalUserUseCaseImpl(
    private val repository: LocalUserRepository
): GetLocalUserUseCase {

    override suspend fun execute(input: Unit) = repository.getUser()
}