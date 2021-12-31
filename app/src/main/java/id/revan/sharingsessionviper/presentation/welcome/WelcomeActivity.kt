package id.revan.sharingsessionviper.presentation.welcome

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import id.revan.sharingsessionviper.R
import id.revan.sharingsessionviper.R.layout
import id.revan.sharingsessionviper.di.WelcomeInjector

class WelcomeActivity : AppCompatActivity(), WelcomeContract.View {

    private lateinit var presenter: WelcomePresenter

    private lateinit var tvName: TextView
    private lateinit var tvUsername: TextView
    private lateinit var tvLocation: TextView
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_welcome)

        WelcomeInjector.provideApplication(application)
        WelcomeInjector.provideActivity(this)
        presenter = WelcomeInjector.provideWelcomePresenter(this)

        tvName = findViewById(R.id.tvName)
        tvUsername = findViewById(R.id.tvUsername)
        tvLocation = findViewById(R.id.tvLocation)
        btnLogout = findViewById(R.id.btnLogout)

        btnLogout.setOnClickListener {
            presenter.logout()
        }

        presenter.getUser()
    }

    override fun onUserLoggedIn(user: Map<String, String>) {
        tvName.text = "Name: ${user["name"]}"
        tvUsername.text = "Username: ${user["username"]}"
        tvLocation.text = "Location: ${user["location"]}"
    }
    
    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}