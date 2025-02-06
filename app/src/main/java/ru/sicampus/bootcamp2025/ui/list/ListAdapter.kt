package ru.sicampus.bootcamp2025.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.ItemUserBinding
import ru.sicampus.bootcamp2025.domain.list.ListEntity

class ListAdapter : PagingDataAdapter<ListEntity, ListAdapter.ViewHolder>(UserDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class ViewHolder(
        private val binding: ItemUserBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListEntity) {
            with(binding) {

                title.text = item.name
                description.text = item.description

                coordinates.text = root.context.getString(
                    R.string.coordinates_format,
                    item.coordinates.latitude,
                    item.coordinates.longitude
                )
            }
        }
    }

    object UserDiff : DiffUtil.ItemCallback<ListEntity>() {
        override fun areItemsTheSame(oldItem: ListEntity, newItem: ListEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ListEntity, newItem: ListEntity): Boolean {
            return oldItem == newItem
        }
    }
}