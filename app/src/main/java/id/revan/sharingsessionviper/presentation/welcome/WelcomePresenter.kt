package id.revan.sharingsessionviper.presentation.welcome

import id.revan.sharingsessionviper.external.CoroutineDispatcherProvider
import id.revan.sharingsessionviper.presentation.welcome.WelcomeContract.Interactor
import id.revan.sharingsessionviper.presentation.welcome.WelcomeContract.Presenter
import id.revan.sharingsessionviper.presentation.welcome.WelcomeContract.Router
import id.revan.sharingsessionviper.presentation.welcome.WelcomeContract.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class WelcomePresenter(
    private var view: View?,
    private var interactor: Interactor?,
    private var router: Router?,
    dispatcherProvider: CoroutineDispatcherProvider
) : Presenter {

    private val scope = CoroutineScope(dispatcherProvider.ui() + SupervisorJob())

    override fun getUser() {
        scope.launch {
            interactor?.getUser()?.run {
                view?.onUserLoggedIn(this)
            }
        }
    }

    override fun logout() {
        scope.launch {
            interactor?.logout()
            router?.navigateToLogin()
        }
    }
    override fun onDestroy() {
        scope.cancel()
        view = null
        interactor = null
        router = null
    }
}