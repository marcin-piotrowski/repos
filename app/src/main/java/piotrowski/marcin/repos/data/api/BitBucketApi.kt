package piotrowski.marcin.repos.data.api

import io.reactivex.Observable
import piotrowski.marcin.repos.data.models.bitbucket.BitBucketResponse
import piotrowski.marcin.repos.util.Constants
import retrofit2.http.GET

interface BitBucketApi {

    @GET(Constants.BITBUCKET_REPOSITORIES_ENDPOINT)
    fun getRepos():
            Observable<BitBucketResponse>
}