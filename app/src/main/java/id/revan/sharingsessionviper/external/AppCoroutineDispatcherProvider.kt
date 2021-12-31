package id.revan.sharingsessionviper.external

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AppCoroutineDispatcherProvider : CoroutineDispatcherProvider {

    override fun ui(): CoroutineDispatcher = Dispatchers.Main

    override fun io(): CoroutineDispatcher = Dispatchers.IO

    override fun default(): CoroutineDispatcher = Dispatchers.Default

    override fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined
}