package repository.auth

import com.nowiwr01p.model.coroutines.dispatchers.AppDispatchers
import kotlinx.coroutines.withContext
import model.errors.auth.AuthTextFieldType
import model.errors.auth.AuthTextFieldType.PASSWORD
import model.errors.auth.AuthTextFieldType.PASSWORD_CONFIRMATION
import model.errors.auth.AuthTextFieldType.PHONE
import model.errors.auth.EmptyFieldsError
import model.errors.auth.PasswordError.PasswordMatchError
import model.user.UserData
import model.user.UserDataSignUp
import validators.PasswordValidator
import validators.PhoneValidator

class AppValidateAuthDataRepositoryImpl(
    private val dispatchers: AppDispatchers,
    private val phoneValidator: PhoneValidator,
    private val passwordValidator: PasswordValidator
): AppValidateAuthDataRepository {

    override suspend fun isAuthDataValid(userData: UserData) = withContext(dispatchers.io) {
        val empty = mutableListOf<AuthTextFieldType>()

        if (userData.phone.isEmpty()) {
            empty.add(PHONE)
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

        phoneValidator.validate(userData.phone)?.let { emailError -> // TODO
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