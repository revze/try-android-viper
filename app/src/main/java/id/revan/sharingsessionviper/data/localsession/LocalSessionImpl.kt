package id.revan.sharingsessionviper.data.localsession

import android.app.Application
import android.content.Context

class LocalSessionImpl(application: Application) : LocalSession {

    private val prefs = application.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    private fun getString(key: String, default: String = ""): String {
        return prefs.getString(key, default) ?: ""
    }

    private fun setString(key: String, value: String) {
        prefs.edit().apply {
            putString(key, value)
        }.apply()
    }

    override fun getName(): String = getString("name")

    override fun setName(name: String) {
        setString("name", name)
    }

    override fun getUsername(): String = getString("username")

    override fun setUsername(username: String) {
        setString("username", username)
    }

    override fun getLocation(): String = getString("location")

    override fun setLocation(location: String) {
        setString("location", location)
    }

    override fun clearSessions() {
        prefs.edit().clear().apply()
    }
}