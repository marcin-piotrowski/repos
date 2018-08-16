package piotrowski.marcin.repos.data.repositories

import android.content.Context
import android.util.Log
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import piotrowski.marcin.repos.data.api.BitBucketApi
import piotrowski.marcin.repos.data.api.GitHubApi
import piotrowski.marcin.repos.data.databases.RepositoriesDB
import piotrowski.marcin.repos.data.models.Repository
import piotrowski.marcin.repos.data.models.bitbucket.BitBucketResponse
import piotrowski.marcin.repos.data.models.github.GitHubRepository

class RepositoriesRepository(private val context: Context) {

    fun getReposById(id: Long): Flowable<Repository> {
        return repositoriesDB.getById(id)
    }

    fun getRepos(): Observable<List<Repository>> {

        val reposList = PublishSubject.create<List<Repository>>()

        repositoriesDB
                .getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ dataBaseResult ->

                    if (dataBaseResult.isEmpty()) {

                        Log.e(this.toString(), "Gonna to fetch")

                        gitHubApi.getRepos()
                                .map({ castFromGitHub(it) })
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({ apisResult ->
                                    apisResult.forEach({ repositoriesDB.insert(it) })

                                    repositoriesDB.getAll()
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe({ result ->
                                                reposList.onNext(result)
                                                Log.e(this.toString(), "Downloaded and saved")
                                            })
                                })

                    } else {
                        Log.e(this.toString(), "Stored data used")
                        reposList.onNext(dataBaseResult)
                    }
                })

        return reposList
    }

    private fun castFromGitHub(list: List<GitHubRepository>): List<Repository> {
        val result = mutableListOf<Repository>()

        list.forEach {
            result.add(
                    Repository(
                            it.owner.login,
                            it.owner.avatar_url,
                            it.name, it.description,
                            "g"))
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
                            it.description,
                            "b"))
        }

        return result
    }

    private val gitHubApi by lazy {
        GitHubApi.create()
    }

    private val bitBucketApi by lazy {
        BitBucketApi.create()
    }

    private val repositoriesDB by lazy {
        RepositoriesDB.create(context)
    }
}