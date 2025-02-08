package ru.sicampus.bootcamp2025.ui.centerList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sicampus.bootcamp2025.databinding.OneCenterListViewBinding
import ru.sicampus.bootcamp2025.domain.center.CenterEntity

class CenterAdapter : ListAdapter<CenterEntity, CenterAdapter.ViewHolder>(CenterDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            OneCenterListViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ViewHolder(
        private val binding: OneCenterListViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
            fun bind(item : CenterEntity) {
                binding.centerTitle.text = item.name
                binding.centerDescription.text = item.description

            }
    }

    object CenterDiff : DiffUtil.ItemCallback<CenterEntity>() {
        override fun areItemsTheSame(oldItem: CenterEntity, newItem: CenterEntity): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CenterEntity, newItem: CenterEntity): Boolean {
            return oldItem == newItem
        }

    }


}