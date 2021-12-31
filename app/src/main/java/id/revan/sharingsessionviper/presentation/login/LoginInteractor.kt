package id.revan.sharingsessionviper.presentation.login

import id.revan.sharingsessionviper.data.entity.User
import id.revan.sharingsessionviper.domain.repository.CommonRepository
import id.revan.sharingsessionviper.external.DataState
import id.revan.sharingsessionviper.presentation.login.LoginContract.Interactor

class LoginInteractor(private val repository: CommonRepository) : Interactor {

    override suspend fun login(username: String, password: String): DataState<User?> {
        return repository.login(username, password)
    }

    override suspend fun isUserLoggedIn(): Boolean {
        val user = repository.getUser()
        return !user["name"].isNullOrBlank()
    }
}