package id.revan.sharingsessionviper.data.entity

import com.squareup.moshi.Json

data class User(
    @Json(name = "name")
    val name: String? = null,

    @Json(name = "username")
    val username: String? = null,

    @Json(name = "location")
    val location: String? = null,
)
