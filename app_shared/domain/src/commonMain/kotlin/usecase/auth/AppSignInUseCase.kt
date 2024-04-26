package usecase.auth

import com.nowiwr01p.model.api.request.auth.SignInRequest
import com.nowiwr01p.model.model.User
import com.nowiwr01p.model.usecase.UseCase

interface AppSignInUseCase: UseCase<SignInRequest, User>