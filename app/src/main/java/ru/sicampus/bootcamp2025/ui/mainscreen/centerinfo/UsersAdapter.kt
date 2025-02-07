package ru.sicampus.bootcamp2025.ui.mainscreen.centerinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.sicampus.bootcamp2025.databinding.VolunteerInCenterItemBinding
import ru.sicampus.bootcamp2025.domain.entities.ProfileEntity

class UsersAdapter: ListAdapter<ProfileEntity, UsersAdapter.ViewHolder>(UserDiff) {

    class ViewHolder(private val binding: VolunteerInCenterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProfileEntity) {
            binding.name.text = item.name
            binding.lastname.text = item.name
            Picasso.get().load(item.photoUrl).into(binding.profilePicture)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(VolunteerInCenterItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object UserDiff : DiffUtil.ItemCallback<ProfileEntity>() {
        override fun areItemsTheSame(oldItem: ProfileEntity, newItem: ProfileEntity): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ProfileEntity, newItem: ProfileEntity): Boolean {
            return oldItem == newItem
        }
    }
}