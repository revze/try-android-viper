package id.revan.sharingsessionviper.external

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatcherProvider {

    fun ui(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
    fun default(): CoroutineDispatcher
    fun unconfined(): CoroutineDispatcher
}