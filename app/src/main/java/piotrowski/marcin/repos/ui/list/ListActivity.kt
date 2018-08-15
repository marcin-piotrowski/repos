package piotrowski.marcin.repos.ui.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list.*
import piotrowski.marcin.repos.R
import piotrowski.marcin.repos.data.models.Repository

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.data.observe(this, Observer<List<Repository>> { reposList ->
            recyclerView.apply {
                adapter = if(reposList == null)
                    ListAdapter(listOf(), context)
                else{
                    ListAdapter(reposList, context)
                }
            }
        })
    }
}
