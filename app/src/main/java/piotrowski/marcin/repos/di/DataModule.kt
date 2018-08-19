package piotrowski.marcin.repos.di

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import piotrowski.marcin.repos.data.api.BitBucketApi
import piotrowski.marcin.repos.data.api.GitHubApi
import piotrowski.marcin.repos.data.databases.repositories.RepositoriesDB
import piotrowski.marcin.repos.util.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideGitHubApi(): GitHubApi = Retrofit
            .Builder()
            .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
            .addConverterFactory(
                    MoshiConverterFactory.create())
            .baseUrl(Constants.GITHUB_MASTER_ENDPOINT)
            .build()
            .create(GitHubApi::class.java)

    @Provides
    @Singleton
    fun provideBitBucketApi(): BitBucketApi = Retrofit
            .Builder()
            .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
            .addConverterFactory(
                    MoshiConverterFactory.create())
            .baseUrl(Constants.BITBUCKET_MASTER_ENDPOINT)
            .build()
            .create(BitBucketApi::class.java)

    @Provides
    @Singleton
    fun provideRepositoriesDB(context: Context) = Room.databaseBuilder(context, RepositoriesDB::class.java, "repositories-db")
            .allowMainThreadQueries()
            .build()
            .repositoryDao()
}