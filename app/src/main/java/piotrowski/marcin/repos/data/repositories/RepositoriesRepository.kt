package piotrowski.marcin.repos.data.repositories

import io.reactivex.Observable
import piotrowski.marcin.repos.data.api.GitHubApi
import piotrowski.marcin.repos.data.models.Repository

object RepositoriesRepository {

    private val gitHubApi by lazy {
        GitHubApi.create()
    }

    fun getReposByPage(page: String): Observable<List<Repository>>{ return gitHubApi.getReposByPage(page)}
}