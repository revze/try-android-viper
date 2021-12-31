package id.revan.sharingsessionviper.data.network

import id.revan.sharingsessionviper.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "${BuildConfig.BASE_URL}/sharing_session/"

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()


    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

}