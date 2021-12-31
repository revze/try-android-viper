package id.revan.sharingsessionviper.data.localsession

interface LocalSession {

    fun getName(): String
    fun setName(name: String)
    fun getUsername(): String
    fun setUsername(username: String)
    fun getLocation(): String
    fun setLocation(location: String)
    fun clearSessions()
}