package piotrowski.marcin.repos.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: Context) {
    @Provides
    @Singleton
    fun provideContext(): Context = app
}