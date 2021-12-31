package id.revan.sharingsessionviper.presentation.login

import id.revan.sharingsessionviper.data.entity.User
import id.revan.sharingsessionviper.external.DataState

interface LoginContract {
    interface View {

        fun showLoading()
        fun hideLoading()
        fun onUserAuthenticated()
        fun showError(message: String)
    }

    interface Presenter {

        fun login(username: String, password: String)
        fun isUserLoggedIn()
        fun navigateToWelcomePage()
        fun onDestroy()
    }

    interface Interactor {

        suspend fun login(username: String, password: String): DataState<User?>
        suspend fun isUserLoggedIn(): Boolean
    }

    interface Router {

        fun navigateToWelcomePage()
    }
}