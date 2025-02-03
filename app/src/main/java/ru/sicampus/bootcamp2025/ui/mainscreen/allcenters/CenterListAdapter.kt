package ru.sicampus.bootcamp2025.ui.mainscreen.allcenters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.sicampus.bootcamp2025.databinding.CenterItemBinding
import ru.sicampus.bootcamp2025.domain.entities.CenterEntity
import kotlin.math.hypot

class CenterListAdapter(private val location: Pair<Double, Double>) :
    ListAdapter<CenterEntity, CenterListAdapter.ViewHolder>(CenterDiff) {

    class ViewHolder(
        private val binding: CenterItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CenterEntity, location: Pair<Double, Double>) {
            binding.name.text = item.name
            binding.address.text = item.address
            binding.tag.text = item.tag
            binding.phone.text = item.phone
            binding.distanceInfo.text =
                "${hypot(item.latitude - location.first, item.longitude - location.second)}"
            Picasso.get().load(item.imageUrl).into(binding.centerImage)
        }
    }

    object CenterDiff : DiffUtil.ItemCallback<CenterEntity>() {
        override fun areContentsTheSame(oldItem: CenterEntity, newItem: CenterEntity): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areItemsTheSame(oldItem: CenterEntity, newItem: CenterEntity): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CenterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), location)
    }
}