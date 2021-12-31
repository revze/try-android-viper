package id.revan.sharingsessionviper.presentation.login

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import id.revan.sharingsessionviper.R
import id.revan.sharingsessionviper.R.layout
import id.revan.sharingsessionviper.di.LoginInjector
import id.revan.sharingsessionviper.presentation.login.LoginContract.View

class LoginActivity : AppCompatActivity(), View {

    private lateinit var edtUsername: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var presenter: LoginPresenter
    private var pd: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_login)

        LoginInjector.provideApplication(application)
        LoginInjector.provideActivity(this)
        presenter = LoginInjector.provideLoginPresenter(this)

        edtUsername = findViewById(R.id.edtUsername)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            presenter.login(edtUsername.text.toString(), edtPassword.text.toString())
        }

        presenter.isUserLoggedIn()
    }

    override fun showLoading() {
        pd = ProgressDialog(this).apply {
            setMessage("Please wait...")
            setProgressStyle(ProgressDialog.STYLE_SPINNER)
            setCancelable(false)
            show()
        }
    }

    override fun hideLoading() {
        pd?.dismiss()
    }

    override fun onUserAuthenticated() {
        presenter.navigateToWelcomePage()
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}