package piotrowski.marcin.repos.util

object Constants {

    //GITHUB
    const val GITHUB_MASTER_ENDPOINT = "https://api.github.com/"
    const val GITHUB_REPOSITORIES_ENDPOINT = "repositories"

    //BITBUCKET
    const val BITBUCKET_MASTER_ENDPOINT = "https://api.bitbucket.org/"
    const val BITBUCKET_REPOSITORIES_ENDPOINT = "2.0/repositories?fields=values.name,values.owner,values.description"
}