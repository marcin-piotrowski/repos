package piotrowski.marcin.repos.ui.detail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.os.Looper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import piotrowski.marcin.repos.data.models.Repository
import piotrowski.marcin.repos.data.repositories.RepositoriesRepository

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RepositoriesRepository = RepositoriesRepository(application.baseContext)
    private val innerData: MutableLiveData<Repository> = MutableLiveData()
    private var id: Long = 0

    fun init(id: Long) {
        this.id = id
    }

    val data: MutableLiveData<Repository>
        get() {
            if (innerData.value == null)
                repository.getReposById(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.from(Looper.getMainLooper()))
                        .subscribe { result -> innerData.value = result }
            return innerData
        }
}