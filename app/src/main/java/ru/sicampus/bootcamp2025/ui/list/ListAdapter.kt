package ru.sicampus.bootcamp2025.ui.list

import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.ItemUserBinding
import ru.sicampus.bootcamp2025.domain.list.ListEntity

class ListAdapter(
    private val context: Context
) : ListAdapter<ListEntity, ru.sicampus.bootcamp2025.ui.list.ListAdapter.ViewHolder>(UserDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemUserBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ListEntity) {
            binding.textName.text = item.name
            binding.textDescription.text = item.description

            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

            val location: Location? = try {
                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            } catch (e: SecurityException) {
                null
            }

            if (location != null) {
                val userLocation = Location("user").apply {
                    latitude = item.coordinates.latitude
                    longitude = item.coordinates.longitude
                }

                val distance = location.distanceTo(userLocation)

                binding.textCoordinates.text = context.getString(R.string.content_distance_format, distance)
            } else {
                binding.textCoordinates.text = context.getString(R.string.content_distance_format)
            }
        }
    }

    object UserDiff : DiffUtil.ItemCallback<ListEntity>() {
        override fun areItemsTheSame(oldItem: ListEntity, newItem: ListEntity): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ListEntity, newItem: ListEntity): Boolean {
            return oldItem == newItem
        }
    }
}