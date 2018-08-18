package piotrowski.marcin.repos.ui.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import piotrowski.marcin.repos.R
import piotrowski.marcin.repos.data.models.Repository

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.init(intent.getLongExtra("id", 0))
        viewModel.data.observe(this, Observer<Repository> {
            repo ->
            Glide.with(this)
                    .load(repo?.ownerAvatarUrl)
                    .into(imgAvatar)
            txtTitle.text = repo?.name
            txtOwner.text = repo?.ownerName
            tatDescription.text = repo?.description })
    }
}
