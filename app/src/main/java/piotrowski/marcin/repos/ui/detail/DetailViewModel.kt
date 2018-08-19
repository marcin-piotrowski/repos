package piotrowski.marcin.repos.ui.detail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import piotrowski.marcin.repos.ReposApp
import piotrowski.marcin.repos.data.models.Repository
import piotrowski.marcin.repos.data.repositories.RepositoriesRepository
import javax.inject.Inject

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var repository: RepositoriesRepository
    private val innerData: MutableLiveData<Repository> = MutableLiveData()
    private var id: Long = 0

    init {
        (application as ReposApp).appComponent.inject(this)
    }

    fun init(id: Long) {
        this.id = id
    }

    val data: MutableLiveData<Repository>
        get() {
            if (innerData.value == null)
                repository.getRepoById(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { result -> innerData.value = result }
            return innerData
        }
}