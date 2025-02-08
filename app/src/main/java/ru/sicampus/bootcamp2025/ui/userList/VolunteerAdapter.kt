package ru.sicampus.bootcamp2025.ui.userList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.sicampus.bootcamp2025.databinding.OneVolunteerListViewBinding
import ru.sicampus.bootcamp2025.domain.user.UserEntity


class VolunteerAdapter : ListAdapter<UserEntity, VolunteerAdapter.ViewHolder>(VolunteerDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            OneVolunteerListViewBinding.inflate(
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
        private val binding: OneVolunteerListViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : UserEntity) {
            binding.nameVolunteer.text = item.name
            Picasso.get().load(item.avatarUrl).resize(100, 100).centerCrop().into(binding.photoVolunteer)
        }
    }
    object VolunteerDiff : DiffUtil.ItemCallback<UserEntity>() {
        override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem == newItem
        }

    }


}
