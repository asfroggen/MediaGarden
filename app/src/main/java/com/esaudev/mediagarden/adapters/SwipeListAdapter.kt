package com.esaudev.mediagarden.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.esaudev.mediagarden.databinding.ListItemBinding
import com.esaudev.mediagarden.databinding.SwipeItemBinding
import com.esaudev.mediagarden.domain.Song
import javax.inject.Inject

open class SwipeListAdapter @Inject constructor(
    private val glide: RequestManager
) : ListAdapter<Song, BaseListViewHolder<*>>(DiffUtilCallback) {

    private object DiffUtilCallback : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean = oldItem.mediaId == newItem.mediaId
        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListViewHolder<*> {
        val itemBinding = SwipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindViewHolderList(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseListViewHolder<*>, position: Int) {
        when (holder) {
            is BindViewHolderList -> holder.bind(getItem(position), position)
        }
    }

    inner class BindViewHolderList(private val binding: SwipeItemBinding) : BaseListViewHolder<Song>(binding.root) {

        override fun bind(item: Song, position: Int) = with(binding) {

            val text = "${item.title} - ${item.subtitle}"
            tvPrimary.text = text

            binding.root.setOnClickListener {
                onItemClickListener?.let { click ->
                    click(item)
                }
            }
        }
    }

    protected var onItemClickListener: ((Song) -> Unit)? = null

    fun setItemClickListener(listener: (Song) -> Unit) {
        onItemClickListener = listener
    }
}