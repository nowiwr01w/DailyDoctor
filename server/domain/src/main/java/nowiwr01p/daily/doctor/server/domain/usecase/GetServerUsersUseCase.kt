package nowiwr01p.daily.doctor.server.domain.usecase

import com.nowiwr01p.model.model.User
import com.nowiwr01p.model.usecase.UseCase

interface GetServerUsersUseCase: UseCase<Unit, List<User>>