package piotrowski.marcin.repos.data.repositories

import io.reactivex.Observable
import piotrowski.marcin.repos.data.api.BitBucketApi
import piotrowski.marcin.repos.data.api.GitHubApi
import piotrowski.marcin.repos.data.models.Repository
import piotrowski.marcin.repos.data.models.bitbucket.BitBucketResponse
import piotrowski.marcin.repos.data.models.github.GitHubRepository

object RepositoriesRepository {

    private val gitHubApi by lazy {
        GitHubApi.create()
    }

    private val bitBucketApi by lazy {
        BitBucketApi.create()
    }

    fun getReposByPage(page: String): Observable<List<Repository>> {
        return gitHubApi.getReposByPage(page)
                .map { castFromGitHub(it) }
                .mergeWith(
                        bitBucketApi.getReposByPage()
                                .map { castFromBitBucket(it) })
    }

    private fun castFromGitHub(list: List<GitHubRepository>): List<Repository> {
        val result = mutableListOf<Repository>()

        list.forEach {
            result.add(
                    Repository(
                            it.owner.login,
                            it.owner.avatar_url,
                            it.name,
                            it.description))
        }

        return result
    }

    private fun castFromBitBucket(response: BitBucketResponse): List<Repository> {
        val result = mutableListOf<Repository>()

        response.values.forEach {
            result.add(
                    Repository(
                            it.owner.name,
                            it.owner.links.avatar.href,
                            it.name,
                            it.description))
        }

        return result
    }
}