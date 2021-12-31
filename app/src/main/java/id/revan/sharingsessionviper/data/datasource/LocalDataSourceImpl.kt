package id.revan.sharingsessionviper.data.datasource

import id.revan.sharingsessionviper.data.localsession.LocalSession
import id.revan.sharingsessionviper.external.CoroutineDispatcherProvider
import kotlinx.coroutines.withContext

class LocalDataSourceImpl(
    private val localSession: LocalSession,
    private val dispatcherProvider: CoroutineDispatcherProvider
) : LocalDataSource {

    override suspend fun setUser(name: String, username: String, location: String) = withContext(dispatcherProvider.io()) {
        localSession.setName(name)
        localSession.setUsername(username)
        localSession.setLocation(location)
    }

    override suspend fun getUser(): Map<String, String> = withContext(dispatcherProvider.io()) {
        hashMapOf(
            "name" to localSession.getName(),
            "username" to localSession.getUsername(),
            "location" to localSession.getLocation()
        )
    }

    override suspend fun clearUserSessions() = withContext(dispatcherProvider.io()) {
        localSession.clearSessions()
    }
}