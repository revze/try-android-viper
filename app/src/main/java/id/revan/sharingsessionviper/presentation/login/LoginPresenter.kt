package id.revan.sharingsessionviper.presentation.login

import id.revan.sharingsessionviper.external.CoroutineDispatcherProvider
import id.revan.sharingsessionviper.external.DataState
import id.revan.sharingsessionviper.presentation.login.LoginContract.Interactor
import id.revan.sharingsessionviper.presentation.login.LoginContract.Presenter
import id.revan.sharingsessionviper.presentation.login.LoginContract.Router
import id.revan.sharingsessionviper.presentation.login.LoginContract.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class LoginPresenter(
    private var view: View?,
    private var interactor: Interactor?,
    private var router: Router?,
    dispatcherProvider: CoroutineDispatcherProvider
) : Presenter {

    private val scope = CoroutineScope(dispatcherProvider.ui() + SupervisorJob())

    override fun login(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            view?.showError("Username and password are required!")
            return
        }
        view?.showLoading()
        scope.launch {
            interactor?.login(username, password)?.let { state ->
                if (state is DataState.Success) {
                    view?.hideLoading()
                    if (state.data != null) {
                        view?.onUserAuthenticated()
                    } else {
                        view?.showError("Invalid username or password!")
                    }
                } else if (state is DataState.Error) {
                    view?.hideLoading()
                    view?.showError("Something went wrong!")
                }
            }
        }
    }

    override fun navigateToWelcomePage() {
        router?.navigateToWelcomePage()
    }

    override fun isUserLoggedIn() {
        scope.launch {
            val isUserLoggedIn = interactor?.isUserLoggedIn() ?: false
            if (isUserLoggedIn) {
                navigateToWelcomePage()
            }
        }
    }

    override fun onDestroy() {
        scope.cancel()
        view = null
        interactor = null
        router = null
    }
}