package repository.auth

import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import kotlinx.coroutines.withContext
import model.errors.auth.AuthTextFieldType
import model.errors.auth.AuthTextFieldType.*
import model.errors.auth.EmptyFieldsError
import model.errors.auth.PasswordError
import model.errors.auth.PasswordError.*
import model.user.UserData
import model.user.UserDataSignUp
import validators.EmailValidator
import validators.PasswordValidator

class AppValidateAuthDataRepositoryImpl(
    private val dispatchers: AppDispatchers,
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator
): AppValidateAuthDataRepository {

    override suspend fun isAuthDataValid(userData: UserData) = withContext(dispatchers.io) {
        val empty = mutableListOf<AuthTextFieldType>()

        if (userData.email.isEmpty()) {
            empty.add(EMAIL)
        }
        if (userData.password.isEmpty()) {
            empty.add(PASSWORD)
        }
        if (userData is UserDataSignUp && userData.passwordRepeated.isEmpty()) {
            empty.add(PASSWORD_CONFIRMATION)
        }

        if (empty.isNotEmpty()) {
            return@withContext EmptyFieldsError(empty)
        }

        emailValidator.validate(userData.email)?.let { emailError ->
            return@withContext emailError
        }
        passwordValidator.validate(userData.password)?.let { passwordError ->
            return@withContext passwordError
        }

        if (userData is UserDataSignUp && userData.password != userData.passwordRepeated) {
            return@withContext PasswordMatchError()
        }

        null
    }
}