package piotrowski.marcin.repos.data.models

import com.squareup.moshi.Json

data class Repository(
        @Json(name = "owner")
        val owner: Owner,
        @Json(name = "name")
        val name: String
)