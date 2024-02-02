package com.example.repoviewer.repo.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy.LOG
import com.example.repoviewer.databinding.RepoItemBinding
import com.example.repoviewer.model.RepositoriesResponse

class RepoAdapter(val context: Context, private val listener: OnRepoClickListener) :
    ListAdapter<RepositoriesResponse?, RepoViewHolder>(RepoDiffUtil()) {
    lateinit var binding: RepoItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val inflater: LayoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = RepoItemBinding.inflate(inflater, parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {

        val currentObject = getItem(position)

        holder.binding.repoNameTxt.text=currentObject?.name  ?:""
        holder.binding.ownerTxt.text=currentObject?.owner?.login ?:""
        holder.binding.desTxt.text=currentObject?.description ?:""
        holder.binding.root.setOnClickListener{
            listener.onRepoClick(currentObject)
        }

        holder.binding.starCountTxt.setOnClickListener {
            listener.onShowStars(currentObject,position)
        }

    }



}

class RepoViewHolder(var binding: RepoItemBinding) : RecyclerView.ViewHolder(binding.root)


class RepoDiffUtil : DiffUtil.ItemCallback<RepositoriesResponse?>() {
    override fun areItemsTheSame(
        oldItem: RepositoriesResponse,
        newItem: RepositoriesResponse
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: RepositoriesResponse,
        newItem: RepositoriesResponse
    ): Boolean {
        return oldItem == newItem
    }

}