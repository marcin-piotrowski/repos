package piotrowski.marcin.repos.util

object Constants {

    //GITHUB
    const val GITHUB_MASTER_ENDPOINT = "https://api.github.com/"
    const val GITHUB_REPOSITORIES_ENDPOINT = "repositories"
    const val GITHUB_API_PARAMETER_SINCE = "since"

    //BITBUCKET
    const val BITBUCKET_MASTER_ENDPOINT = "https://api.bitbucket.org/2.0/"
    const val BITBUCKET_REPOSITORIES_ENDPOINT = "repositories?fields=values.name,values.owner,values.description"
}