package id.revan.sharingsessionviper.data.repository

import id.revan.sharingsessionviper.data.datasource.LocalDataSource
import id.revan.sharingsessionviper.data.datasource.RemoteDataSource
import id.revan.sharingsessionviper.data.entity.User
import id.revan.sharingsessionviper.domain.repository.CommonRepository
import id.revan.sharingsessionviper.external.DataState

class CommonRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : CommonRepository {

    override suspend fun login(username: String, password: String): DataState<User?> {
        return remoteDataSource.login(username, password).apply {
            if (this is DataState.Success) {
                this.data?.let {
                    localDataSource.setUser(it.name ?: "", it.username ?: "", it.location ?: "")
                }
            }
        }
    }

    override suspend fun getUser(): Map<String, String> {
        return localDataSource.getUser()
    }

    override suspend fun clearUserSessions() {
        localDataSource.clearUserSessions()
    }
}