package usecase.auth

import com.nowiwr01p.model.api.request.SignUpRequest
import com.nowiwr01p.model.model.User
import com.nowiwr01p.model.usecase.UseCase

interface AppSignUpUseCase: UseCase<SignUpRequest, User>