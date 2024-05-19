package nowiwr01p.daily.doctor.server.token.common.usecase

import nowiwr01p.daily.doctor.server.token.common.repository.ServerCommonTokenRepository

class ServerGenerateCommonTokenUseCaseImpl(
    private val repository: ServerCommonTokenRepository
): ServerGenerateCommonTokenUseCase {

    override suspend fun execute(input: Unit) = repository.generateToken()
}