package com.example.repoviewer.issues.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.repoviewer.databinding.IssuesItemBinding
import com.example.repoviewer.model.IssuesResponse


class IssuesAdapter(val context: Context ) :
    ListAdapter<IssuesResponse?, IssuesViewHolder>(IssuesDiffUtil()) {
    lateinit var binding: IssuesItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssuesViewHolder {
        val inflater: LayoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = IssuesItemBinding.inflate(inflater, parent, false)
        return IssuesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IssuesViewHolder, position: Int) {

        val currentObject = getItem(position)

        holder.binding.titleTxt.text=currentObject?.title  ?:""
        holder.binding.authorTxt.text=currentObject?.user?.login ?:""
        holder.binding.createdAtTxt.text=currentObject?.createdAt?.substringAfter("T") ?:""
        holder.binding.updatedAtTxt.text=currentObject?.updatedAt?.substringAfter("T") ?:""
        holder.binding.stateTxt.text=currentObject?.state
    }

}

class IssuesViewHolder(var binding: IssuesItemBinding) : RecyclerView.ViewHolder(binding.root)


class IssuesDiffUtil : DiffUtil.ItemCallback<IssuesResponse?>() {
    override fun areItemsTheSame(
        oldItem: IssuesResponse,
        newItem: IssuesResponse
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: IssuesResponse,
        newItem: IssuesResponse
    ): Boolean {
        return oldItem == newItem
    }

}