package nowiwr01p.daily.doctor.server.data.usecase.auth

import com.nowiwr01p.model.api.request.auth.SignInRequest
import nowiwr01p.daily.doctor.server.domain.repository.auth.ServerAuthRepository
import nowiwr01p.daily.doctor.server.domain.usecase.auth.ServerSignInUseCase

class ServerSignInUseCaseImpl(
    private val repository: ServerAuthRepository
): ServerSignInUseCase {

    override suspend fun execute(input: SignInRequest) = repository.signIn(input)
}