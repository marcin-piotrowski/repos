package piotrowski.marcin.repos.data.api

import io.reactivex.Observable
import piotrowski.marcin.repos.data.models.Repository
import piotrowski.marcin.repos.util.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {

    @GET(Constants.GITHUB_REPOSITORIES_ENDPOINT)
    fun getReposByPage(@Query(Constants.GITHUB_ADI_PARAMETER_SINCE) since: String):
            Observable<List<Repository>>

    companion object {
        fun create(): GitHubApi {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            MoshiConverterFactory.create())
                    .baseUrl(Constants.GITHUB_MASTER_ENDPOINT)
                    .build()

            return retrofit.create(GitHubApi::class.java)
        }
    }
}