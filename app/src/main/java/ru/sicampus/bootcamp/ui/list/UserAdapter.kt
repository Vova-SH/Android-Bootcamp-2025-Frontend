package ru.sicampus.bootcamp.ui.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sicampus.bootcamp.data.auth.login
import ru.sicampus.bootcamp.databinding.UserCardItemBinding
import ru.sicampus.bootcamp.domain.list.UserEntity

interface OpenProfile {
    fun goToProfile(username: String)
}

class UserAdapter(private val listener: OpenProfile) : ListAdapter<UserEntity, UserAdapter.ViewHolder>(UserDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            UserCardItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: UserCardItemBinding,
        private val listener: OpenProfile
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserEntity) {
            binding.btn.setOnClickListener{
                listener.goToProfile(item.username)
                login.login1 = item.username
                Log.d("ESEESE", "${login.login1}")
            }
            binding.title.text = item.username
            binding.discription.text = item.email
        }

    }

    object UserDiff : DiffUtil.ItemCallback<UserEntity>() {
        override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem == newItem
        }
    }
}
