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
import id.revan.sharingsessionviper.presentation.welcome.WelcomeContract
import id.revan.sharingsessionviper.presentation.welcome.WelcomeInteractor
import id.revan.sharingsessionviper.presentation.welcome.WelcomePresenter
import id.revan.sharingsessionviper.presentation.welcome.WelcomeRouter

object WelcomeInjector {

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

    private fun provideRouter(): WelcomeContract.Router = WelcomeRouter(activity!!)

    private fun provideInteractor(): WelcomeContract.Interactor = WelcomeInteractor(provideCommonRepository())

    fun provideWelcomePresenter(
        view: WelcomeContract.View?,
    ): WelcomePresenter =
        WelcomePresenter(view, provideInteractor(), provideRouter(), provideCoroutineDispatcherProvider())
}