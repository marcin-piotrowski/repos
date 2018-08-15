package piotrowski.marcin.repos.data.models.bitbucket

import com.squareup.moshi.Json

data class BitBucketResponse (
        @Json(name = "values")
        val values: List<BitBucketRepository>
)