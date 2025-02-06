package ru.sicampus.bootcamp2025.ui.list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.sicampus.bootcamp2025.databinding.ItemUserBinding
import ru.sicampus.bootcamp2025.domain.list.UserEntity

class UserAdapter: PagingDataAdapter<UserEntity, UserAdapter.ViewHolder>(UserDiff) {

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
        holder.bind(getItem(position) ?:
        UserEntity(id = "-1",
            name = "Loading...",
            email = "",
            photoUrl = "")
        )
    }


    class ViewHolder(
        private val binding: ItemUserBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserEntity){
            binding.title.text = "${item.id}, ${item.name}"
            binding.description.text = item.email
            if (item.photoUrl.isNotEmpty()) {
                Picasso.get().load(item.photoUrl)
                    .resize(64, 64)
                    .centerCrop()
                    .into(binding.photo)
            } else {
                binding.photo.setBackgroundColor(Color.WHITE)
            }
        }
    }

    object UserDiff : DiffUtil.ItemCallback<UserEntity>() {
        override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem == newItem
        }

    }
}