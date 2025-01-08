package usecase.auth

import com.nowiwr01p.model.usecase.UseCase
import model.errors.auth.AuthError
import model.user.UserData

interface AppValidateAuthDataUseCase: UseCase<UserData, AuthError?>