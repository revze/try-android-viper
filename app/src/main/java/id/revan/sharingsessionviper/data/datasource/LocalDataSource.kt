package id.revan.sharingsessionviper.data.datasource

interface LocalDataSource {

    suspend fun setUser(name: String, username: String, location: String)
    suspend fun getUser(): Map<String, String>
    suspend fun clearUserSessions()
}