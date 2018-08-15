package piotrowski.marcin.repos.data.models.bitbucket

data class BitBucketRepositoryOwner (
        val links: LinkList,
        val name: String
)

data class LinkList(val avatar: Avatar)

data class Avatar(val href: String)