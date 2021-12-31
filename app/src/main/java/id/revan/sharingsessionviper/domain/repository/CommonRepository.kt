package id.revan.sharingsessionviper.domain.repository

import id.revan.sharingsessionviper.data.entity.User
import id.revan.sharingsessionviper.external.DataState

interface CommonRepository {

    suspend fun login(username: String, password: String): DataState<User?>
    suspend fun getUser(): Map<String, String>
    suspend fun clearUserSessions()
}


