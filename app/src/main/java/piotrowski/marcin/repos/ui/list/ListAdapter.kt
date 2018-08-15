package piotrowski.marcin.repos.ui.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_repository.view.*
import piotrowski.marcin.repos.R
import piotrowski.marcin.repos.data.models.Repository

class ListAdapter(private val dataset: List<Repository>, private val context: Context) :
        RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val avatarImgView = view.imgAvatar
        val titleTxtView = view.txtTitle
        val ownerTxtView = view.txtOwner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder{
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_repository, parent, false) as View
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
                .load(dataset[position].owner.avatar_url)
                .into(holder.avatarImgView)
        holder.titleTxtView.text = dataset[position].name
        holder.ownerTxtView.text = dataset[position].owner.login
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}