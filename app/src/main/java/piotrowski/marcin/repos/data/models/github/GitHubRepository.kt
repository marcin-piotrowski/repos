package piotrowski.marcin.repos.data.models.github

import com.squareup.moshi.Json

data class GitHubRepository(
        @Json(name = "owner")
        val owner: GitHubRepositoryOwner,
        @Json(name = "name")
        val name: String,
        @Json(name = "description")
        val description: String?
)