package piotrowski.marcin.repos.di

import dagger.Component
import piotrowski.marcin.repos.ui.detail.DetailViewModel
import piotrowski.marcin.repos.ui.list.ListViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface AppComponent{
    fun inject(target: ListViewModel)
    fun inject(target: DetailViewModel)
}