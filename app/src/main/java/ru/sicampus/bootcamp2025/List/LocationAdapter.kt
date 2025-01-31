package ru.sicampus.bootcamp2025.List



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.sicampus.bootcamp2025.R
import java.text.DecimalFormat

class LocationAdapter(private val locations: List<Location>) :
    RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.name)
        val distanceText: TextView = itemView.findViewById(R.id.distance)
        val populationText: TextView = itemView.findViewById(R.id.population)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_location, parent, false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location = locations[position]
        val decimalFormat = DecimalFormat("#.##")

        holder.nameText.text = location.name
        holder.distanceText.text = "${decimalFormat.format(location.distance)} км"
        holder.populationText.text = "${location.population} чел."
    }

    override fun getItemCount() = locations.size
}