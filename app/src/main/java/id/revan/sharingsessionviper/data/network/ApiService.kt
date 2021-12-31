package id.revan.sharingsessionviper.data.network

import id.revan.sharingsessionviper.data.entity.LoginRes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("login.php")
    suspend fun login(@Query("username") username: String, @Query("password") password: String): Response<LoginRes>
}