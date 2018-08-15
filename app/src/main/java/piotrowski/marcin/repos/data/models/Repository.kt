package piotrowski.marcin.repos.data.models

data class Repository(
        val ownerName: String,
        val ownerAvatarUrl: String,
        val name: String,
        val description: String?
)