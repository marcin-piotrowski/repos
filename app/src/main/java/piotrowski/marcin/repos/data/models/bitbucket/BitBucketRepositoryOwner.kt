package piotrowski.marcin.repos.data.models.bitbucket

import com.squareup.moshi.Json

data class BitBucketRepositoryOwner (
        @Json(name = "links")
        val links: LinkList,
        @Json(name = "username")
        val username: String
)

data class LinkList(@Json(name = "avatar") val avatar: Avatar)

data class Avatar(@Json(name = "href") val href: String)