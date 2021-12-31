package id.revan.sharingsessionviper.di

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import id.revan.sharingsessionviper.data.datasource.LocalDataSource
import id.revan.sharingsessionviper.data.datasource.LocalDataSourceImpl
import id.revan.sharingsessionviper.data.datasource.RemoteDataSource
import id.revan.sharingsessionviper.data.datasource.RemoteDataSourceImpl
import id.revan.sharingsessionviper.data.localsession.LocalSession
import id.revan.sharingsessionviper.data.localsession.LocalSessionImpl
import id.revan.sharingsessionviper.data.network.ApiService
import id.revan.sharingsessionviper.data.network.RetrofitBuilder
import id.revan.sharingsessionviper.data.repository.CommonRepositoryImpl
import id.revan.sharingsessionviper.domain.repository.CommonRepository
import id.revan.sharingsessionviper.external.AppCoroutineDispatcherProvider
import id.revan.sharingsessionviper.external.CoroutineDispatcherProvider
import id.revan.sharingsessionviper.presentation.login.LoginContract
import id.revan.sharingsessionviper.presentation.login.LoginInteractor
import id.revan.sharingsessionviper.presentation.login.LoginPresenter
import id.revan.sharingsessionviper.presentation.login.LoginRouter

object LoginInjector {

    private var app: Application? = null
    private var activity: AppCompatActivity? = null

    fun provideApplication(application: Application) {
        app = application
    }

    fun provideActivity(activity: AppCompatActivity) {
        this.activity = activity
    }

    private fun provideCoroutineDispatcherProvider(): CoroutineDispatcherProvider = AppCoroutineDispatcherProvider()

    private fun provideLocalSession(): LocalSession = LocalSessionImpl(app!!)

    private fun provideApiService(): ApiService = RetrofitBuilder.apiService

    private fun provideRemoteDataSource(): RemoteDataSource =
        RemoteDataSourceImpl(provideApiService(), provideCoroutineDispatcherProvider())

    private fun provideLocalDataSource(): LocalDataSource =
        LocalDataSourceImpl(provideLocalSession(), provideCoroutineDispatcherProvider())

    private fun provideCommonRepository(): CommonRepository =
        CommonRepositoryImpl(provideRemoteDataSource(), provideLocalDataSource())

    private fun provideRouter(): LoginContract.Router = LoginRouter(activity!!)

    private fun provideInteractor(): LoginContract.Interactor = LoginInteractor(provideCommonRepository())

    fun provideLoginPresenter(
        view: LoginContract.View?,
    ): LoginPresenter =
        LoginPresenter(view, provideInteractor(), provideRouter(), provideCoroutineDispatcherProvider())
}