package data.repository.auth

import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import domain.model.user.UserData
import domain.model.user.UserDataSignUp
import domain.repository.auth.ValidateAuthDataRepository
import domain.repository.auth.data.errors.AuthTextFieldType
import domain.repository.auth.data.errors.AuthTextFieldType.EMAIL
import domain.repository.auth.data.errors.AuthTextFieldType.PASSWORD
import domain.repository.auth.data.errors.AuthTextFieldType.PASSWORD_CONFIRMATION
import domain.repository.auth.data.errors.EmptyFieldsError
import domain.repository.auth.data.errors.PasswordError.PasswordMatchError
import domain.validators.EmailValidator
import domain.validators.PasswordValidator
import kotlinx.coroutines.withContext

class ValidateAuthDataRepositoryImpl(
    private val dispatchers: AppDispatchers,
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator
): ValidateAuthDataRepository {

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