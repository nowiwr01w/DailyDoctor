package user.repository

import com.nowiwr01p.model.model.User
import com.nowiwr01p.model.repository.BaseRepository
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.SuspendSettings
import kotlinx.serialization.encodeToString

@OptIn(ExperimentalSettingsApi::class)
class LocalUserRepositoryImpl(
    private val settings: SuspendSettings
): BaseRepository(), LocalUserRepository {

    override suspend fun getUser() = io {
        val userString = settings.getString(key = USER_OBJECT_KEY, defaultValue = "")
        getModelFromString<User>(userString)
    }

    override suspend fun saveUser(user: User) = io {
        val userString = json.encodeToString(user)
        settings.putString(key = USER_OBJECT_KEY, value = userString)
    }

    private companion object {
        const val USER_OBJECT_KEY = "User"
    }
}