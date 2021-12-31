package id.revan.sharingsessionviper.presentation.welcome

interface WelcomeContract {
    interface View {

        fun onUserLoggedIn(user: Map<String, String>)
    }

    interface Presenter {

        fun getUser()
        fun logout()
        fun onDestroy()
    }

    interface Interactor {

        suspend fun getUser(): Map<String, String>
        suspend fun logout()
    }

    interface Router {

        fun navigateToLogin()
    }
}