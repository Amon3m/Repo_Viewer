package com.example.repoviewer.repo.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.example.repoviewer.databinding.RepoItemBinding
import com.example.repoviewer.model.RepositoryEntity

class RepoAdapter(val context: Context, private val listener: OnRepoClickListener) :
    ListAdapter<RepositoryEntity?, RepoViewHolder>(RepoDiffUtil()) {
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
        holder.binding.ownerTxt.text=currentObject?.owner ?:""
        holder.binding.desTxt.text=currentObject?.description ?:""


        holder.binding.starCountTxt.text= currentObject?.starCount ?:"show stars"

        holder.binding.root.setOnClickListener{
            listener.onRepoClick(currentObject)
        }

        holder.binding.starCountTxt.setOnClickListener {
            listener.onShowStars(currentObject)
        }

    }



}

class RepoViewHolder(var binding: RepoItemBinding) : RecyclerView.ViewHolder(binding.root)


class RepoDiffUtil : DiffUtil.ItemCallback<RepositoryEntity?>() {
    override fun areItemsTheSame(
        oldItem: RepositoryEntity,
        newItem: RepositoryEntity
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: RepositoryEntity,
        newItem: RepositoryEntity
    ): Boolean {
        return oldItem == newItem
    }

}