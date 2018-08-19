package piotrowski.marcin.repos.data.api

import io.reactivex.Observable
import piotrowski.marcin.repos.data.models.github.GitHubRepository
import piotrowski.marcin.repos.util.Constants
import retrofit2.http.GET

interface GitHubApi {

    @GET(Constants.GITHUB_REPOSITORIES_ENDPOINT)
    fun getRepos():
            Observable<List<GitHubRepository>>
}