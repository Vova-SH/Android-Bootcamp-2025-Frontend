package ru.sicampus.bootcamp2025.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.ItemCenterBinding
import ru.sicampus.bootcamp2025.domain.list.ListEntity
import ru.sicampus.bootcamp2025.domain.one.OneCenter
import ru.sicampus.bootcamp2025.ui.one.OneCenterFragment

class ListAdapter(
    private val fragmentManager: FragmentManager
) : PagingDataAdapter<ListEntity, ListAdapter.ViewHolder>(UserDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCenterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            fragmentManager
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class ViewHolder(
        private val binding: ItemCenterBinding,
        private val fragmentManager: FragmentManager
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

                val oneCenter = OneCenter(
                    id = item.id,
                    name = item.name,
                    description = item.description,
                    coordinates = item.coordinates
                )

                binding.actionButton.setOnClickListener {
                    fragmentManager.beginTransaction()
                        .replace(R.id.main, OneCenterFragment.newInstance(oneCenter))
                        .commitAllowingStateLoss()
                }
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