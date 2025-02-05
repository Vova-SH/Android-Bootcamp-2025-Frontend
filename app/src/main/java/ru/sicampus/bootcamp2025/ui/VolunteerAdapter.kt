package ru.sicampus.bootcamp2025.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.sicampus.bootcamp2025.databinding.ItemVolunteerBinding
import ru.sicampus.bootcamp2025.domain.VolunteerEntity

class VolunteerAdapter: ListAdapter<VolunteerEntity, VolunteerAdapter.ViewHolder>(UserDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemVolunteerBinding.inflate(
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
        private val binding: ItemVolunteerBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: VolunteerEntity) {
            binding.title.text = item.name
            binding.description.text = item.email

        }

    }

    object UserDiff : DiffUtil.ItemCallback<VolunteerEntity>() {
        override fun areItemsTheSame(oldItem: VolunteerEntity, newItem: VolunteerEntity): Boolean {
            return oldItem.name == newItem.name
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: VolunteerEntity, newItem: VolunteerEntity): Boolean {
            return oldItem == newItem
        }
    }

}