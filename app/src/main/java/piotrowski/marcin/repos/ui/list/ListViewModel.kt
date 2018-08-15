package piotrowski.marcin.repos.ui.list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import piotrowski.marcin.repos.data.models.Repository
import piotrowski.marcin.repos.data.models.github.GitHubRepository
import piotrowski.marcin.repos.data.repositories.RepositoriesRepository

class ListViewModel : ViewModel() {

    private var currentPage: String = "1"

    private var innerData:MutableLiveData<List<Repository>> = MutableLiveData()

    var data: MutableLiveData<List<Repository>> = MutableLiveData()
        get() {
            if (innerData.value == null)
                RepositoriesRepository.getReposByPage(currentPage)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> innerData.value = result },
                                { error -> Log.e("ERROR!", "data fetching failed! $error") }
                        )
            return innerData
        }
}