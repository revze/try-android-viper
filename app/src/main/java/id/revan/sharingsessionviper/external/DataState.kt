package id.revan.sharingsessionviper.external

sealed class DataState<out R> {

    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val throwable: Throwable? = null, val code: Int = 0) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}