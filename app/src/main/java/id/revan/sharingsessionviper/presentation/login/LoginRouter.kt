package id.revan.sharingsessionviper.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import id.revan.sharingsessionviper.presentation.welcome.WelcomeActivity
import id.revan.sharingsessionviper.presentation.login.LoginContract.Router

class LoginRouter(private val activity: AppCompatActivity) : Router {

    override fun navigateToWelcomePage() {
        with(activity) {
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()
        }
    }
}