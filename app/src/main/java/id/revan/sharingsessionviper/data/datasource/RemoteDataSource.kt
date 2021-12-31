package id.revan.sharingsessionviper.data.datasource

import id.revan.sharingsessionviper.data.entity.User
import id.revan.sharingsessionviper.external.DataState

interface RemoteDataSource {

    suspend fun login(username: String, password: String): DataState<User?>
}