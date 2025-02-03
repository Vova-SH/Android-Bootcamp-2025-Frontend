package ru.sicampus.bootcamp2025.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.model.Volunteer

class VolunteerAdapter(private var volunteers: List<Volunteer>) : RecyclerView.Adapter<VolunteerAdapter.VolunteerViewHolder>() {

    class VolunteerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.volunteerName)

        fun bind(volunteer: Volunteer) {
            nameTextView.text = volunteer.name // Предполагается, что у Volunteer есть поле name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VolunteerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_volunteer, parent, false)
        return VolunteerViewHolder(view)
    }

    override fun onBindViewHolder(holder: VolunteerViewHolder, position: Int) {
        holder.bind(volunteers[position])
    }

    override fun getItemCount(): Int {
        return volunteers.size
    }

    fun updateVolunteers(newVolunteers: List<Volunteer>) {
        volunteers = newVolunteers
        notifyDataSetChanged() // Обновляем данные в адаптере
    }
}