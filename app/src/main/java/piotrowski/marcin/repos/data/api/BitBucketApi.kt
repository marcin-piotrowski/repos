package piotrowski.marcin.repos.data.api

import io.reactivex.Observable
import piotrowski.marcin.repos.data.models.bitbucket.BitBucketResponse
import piotrowski.marcin.repos.util.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface BitBucketApi {

    @GET(Constants.BITBUCKET_REPOSITORIES_ENDPOINT)
    fun getReposByPage():
            Observable<BitBucketResponse>

    companion object {
        fun create(): BitBucketApi {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            MoshiConverterFactory.create())
                    .baseUrl(Constants.BITBUCKET_MASTER_ENDPOINT)
                    .build()

            return retrofit.create(BitBucketApi::class.java)
        }
    }
}