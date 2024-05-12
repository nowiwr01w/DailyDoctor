package nowiwr01p.daily.doctor.server.data.usecase.auth

import com.nowiwr01p.model.api.request.auth.SignUpRequest
import nowiwr01p.daily.doctor.server.domain.repository.auth.ServerAuthRepository
import nowiwr01p.daily.doctor.server.domain.usecase.auth.ServerSignUpUseCase

class ServerSignUpUseCaseImpl(
    private val repository: ServerAuthRepository
): ServerSignUpUseCase {

    override suspend fun execute(input: SignUpRequest) = repository.signUp(input)
}