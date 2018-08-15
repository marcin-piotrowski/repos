package piotrowski.marcin.repos.data.models.bitbucket

import com.squareup.moshi.Json

data class BitBucketRepository (
        @Json(name = "owner")
        val owner: BitBucketRepositoryOwner,
        @Json(name = "name")
        val name: String,
        @Json(name = "description")
        val description: String
)