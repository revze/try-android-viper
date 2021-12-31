package id.revan.sharingsessionviper.data.datasource

import id.revan.sharingsessionviper.data.entity.User
import id.revan.sharingsessionviper.data.network.ApiService
import id.revan.sharingsessionviper.external.CoroutineDispatcherProvider
import id.revan.sharingsessionviper.external.DataState
import kotlinx.coroutines.withContext

class RemoteDataSourceImpl(
    private val apiService: ApiService,
    private val dispatcherProvider: CoroutineDispatcherProvider
) : RemoteDataSource {

    override suspend fun login(username: String, password: String): DataState<User?> = withContext(dispatcherProvider.io()) {
        try {
            val result = apiService.login(username, password)
            if (result.isSuccessful) {
                DataState.Success(result.body()?.data)
            } else {
                DataState.Error(code = result.code())
            }
        } catch (e: Throwable) {
            DataState.Error(e)
        }
    }
}