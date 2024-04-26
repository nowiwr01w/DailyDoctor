package nowiwr01p.daily.doctor.server.data.usecase

import com.nowiwr01p.model.api.request.auth.SignInRequest
import nowiwr01p.daily.doctor.server.domain.repository.user.UserRepositoryServer
import nowiwr01p.daily.doctor.server.domain.usecase.ServerSignInUseCase

class ServerSignInUseCaseImpl(
    private val repository: UserRepositoryServer
): ServerSignInUseCase {

    override suspend fun execute(input: SignInRequest) = repository.signIn(input)
}