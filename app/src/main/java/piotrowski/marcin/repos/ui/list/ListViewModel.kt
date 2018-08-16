package piotrowski.marcin.repos.ui.list

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import piotrowski.marcin.repos.data.models.Repository
import piotrowski.marcin.repos.data.repositories.RepositoriesRepository

class ListViewModel(application: Application) : AndroidViewModel(application) {

    private var isLoading: Boolean = false
    private var isSorted: Boolean = false
    private var originalList: List<Repository>? = emptyList()

    private val repository: RepositoriesRepository = RepositoriesRepository(application.baseContext)
    private val innerData: MutableLiveData<List<Repository>> = MutableLiveData()

    val data: MutableLiveData<List<Repository>>
        get() {
            if (innerData.value == null)
                repository.getRepos()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ result ->
                            innerData.value = result
                        })

            return innerData
        }

    fun loadMore() {
        if (!isLoading) {
            isLoading = true
            repository.getRepos().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ result ->
                        innerData.value = innerData.value?.plus(result)
                        isLoading = false
                    })
        }
    }

    fun sort(){
        if(isSorted){
            data.value = originalList
            originalList = emptyList()
            isSorted = false
        }else{
            originalList = data.value
            data.value = data.value?.sortedBy { it.name }
            isSorted = true
        }
    }
}