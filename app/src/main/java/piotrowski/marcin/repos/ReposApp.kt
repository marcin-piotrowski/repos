package piotrowski.marcin.repos

import android.app.Application
import piotrowski.marcin.repos.di.AppComponent
import piotrowski.marcin.repos.di.AppModule
import piotrowski.marcin.repos.di.DaggerAppComponent

class ReposApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
    }

    private fun initDagger(app: ReposApp): AppComponent =
            DaggerAppComponent.builder()
                    .appModule(AppModule(app))
                    .build()
}