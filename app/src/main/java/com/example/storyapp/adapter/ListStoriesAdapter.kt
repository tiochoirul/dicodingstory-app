package com.example.storyapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.storyapp.data.remote.response.ListStoryItem
import com.example.storyapp.databinding.ItemStoriesListBinding
import com.example.storyapp.tools.withDateFormat

class ListStoriesAdapter(private val uploadedOn: String) :
    PagingDataAdapter<ListStoryItem, ListStoriesAdapter.ListViewHolder>(
        DIFF_CALLBACK
    ) {

    var onItemClick: ((View, ListStoryItem) -> Unit)? = null

    inner class ListViewHolder(
        var binding: ItemStoriesListBinding,
        private val uploadedOn: String
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.rootView.setOnClickListener { itemRootView ->
                val story = getItem(bindingAdapterPosition)
                story?.let {
                    onItemClick?.invoke(itemRootView, it)
                }
            }
        }

        fun bind(data: ListStoryItem) {
            binding.apply {
                Glide.with(itemView)
                    .load(data.photoUrl)
                    .into(ivItemPhoto)

                val uploaded = StringBuilder(uploadedOn).append(" ")
                    .append(data.createdAt.withDateFormat())

                tvItemName.text = data.name
                tvDescription.text = data.description
                tvUploaded.text = uploaded
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemStoriesListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListViewHolder(binding, uploadedOn)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListStoryItem>() {
            override fun areItemsTheSame(oldItem: ListStoryItem, newItem: ListStoryItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ListStoryItem,
                newItem: ListStoryItem
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }


}