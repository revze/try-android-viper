package id.revan.sharingsessionviper.data.entity

import com.squareup.moshi.Json

data class LoginRes (
    @Json(name = "data")
    val data: User? = null,

    @Json(name = "message")
    val message: String? = null
)