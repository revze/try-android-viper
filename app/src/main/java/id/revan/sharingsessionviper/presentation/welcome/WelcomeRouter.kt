package id.revan.sharingsessionviper.presentation.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import id.revan.sharingsessionviper.presentation.login.LoginActivity
import id.revan.sharingsessionviper.presentation.welcome.WelcomeContract.Router

class WelcomeRouter(private val activity: AppCompatActivity) : Router {

    override fun navigateToLogin() {
        with(activity) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}