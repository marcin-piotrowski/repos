package piotrowski.marcin.repos.data.models

import com.squareup.moshi.Json

data class Owner(
        @Json(name = "login")
        val login: String,
        @Json(name = "avatar_url")
        val avatar_url: String
)