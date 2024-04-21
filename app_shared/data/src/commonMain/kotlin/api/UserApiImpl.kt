package api

import api.user.UserApi
import com.nowiwr01p.model.model.User
import org.kodein.di.DI

class UserApiImpl(kodein: DI): BaseApi(kodein), UserApi {

    override suspend fun getUsers(): List<User> {
        return getHttp<List<User>>(route = "users")
    }

    override suspend fun getUserById(id: Int): User {
        return getHttp<User>(route = "user/$id")
    }
}