package piotrowski.marcin.repos.data.repositories

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import piotrowski.marcin.repos.data.api.BitBucketApi
import piotrowski.marcin.repos.data.api.GitHubApi
import piotrowski.marcin.repos.data.dao.RepositoryDao
import piotrowski.marcin.repos.data.models.Repository
import piotrowski.marcin.repos.data.models.bitbucket.BitBucketResponse
import piotrowski.marcin.repos.data.models.github.GitHubRepository
import javax.inject.Inject

class RepositoriesRepository @Inject constructor(private val gitHubApi: GitHubApi,
                                                 private val bitBucketApi: BitBucketApi,
                                                 private val repositoriesDB: RepositoryDao) {

    fun getRepoById(id: Long): Single<Repository> {
        return repositoriesDB.getById(id)
    }

    fun getRepos(): Flowable<List<Repository>> {

        val reposFlowable = repositoriesDB.getAll()

        reposFlowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ dataBaseResult ->
                    if (dataBaseResult.isEmpty()) {
                        gitHubApi.getRepos()
                                .map { castFromGitHub(it) }
                                .mergeWith(
                                        bitBucketApi.getRepos()
                                                .map { castFromBitBucket(it) }
                                )
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe { apisResult ->
                                    reposFlowable.unsubscribeOn(AndroidSchedulers.mainThread())
                                    apisResult.forEach({ repositoriesDB.insert(it) })
                                }
                    }
                })

        return repositoriesDB.getAll()
    }

    private fun castFromGitHub(list: List<GitHubRepository>): List<Repository> {
        val result = mutableListOf<Repository>()

        list.forEach {
            result.add(
                    Repository(
                            it.owner.login,
                            it.owner.avatar_url,
                            it.name,
                            it.description,
                            Repository.Source.GITHUB))
        }

        return result
    }

    private fun castFromBitBucket(response: BitBucketResponse): List<Repository> {
        val result = mutableListOf<Repository>()

        response.values.forEach {
            result.add(
                    Repository(
                            it.owner.username,
                            it.owner.links.avatar.href,
                            it.name,
                            it.description,
                            Repository.Source.BITBUCKET))
        }

        return result
    }
}