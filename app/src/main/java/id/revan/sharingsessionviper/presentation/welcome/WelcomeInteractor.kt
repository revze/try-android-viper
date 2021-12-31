package id.revan.sharingsessionviper.presentation.welcome

import id.revan.sharingsessionviper.domain.repository.CommonRepository
import id.revan.sharingsessionviper.presentation.welcome.WelcomeContract.Interactor

class WelcomeInteractor(private val repository: CommonRepository) : Interactor {

    override suspend fun getUser(): Map<String, String> = repository.getUser()

    override suspend fun logout() {
        repository.clearUserSessions()
    }
}