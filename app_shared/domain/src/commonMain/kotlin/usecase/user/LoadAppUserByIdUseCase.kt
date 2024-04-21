package usecase.user

import com.nowiwr01p.model.model.User
import com.nowiwr01p.model.usecase.UseCase

interface LoadAppUserByIdUseCase: UseCase<Int, User>