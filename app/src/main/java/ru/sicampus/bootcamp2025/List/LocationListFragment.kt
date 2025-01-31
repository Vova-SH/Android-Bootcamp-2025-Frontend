package ru.sicampus.bootcamp2025.List

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.sicampus.bootcamp2025.R

class LocationListFragment : Fragment() {
    private val locations = listOf(
        Location("1", 0.0, 12_500_000),
        Location("2", 700.0, 5_400_000),
        Location("3", 3_300.0, 1_600_000),
        Location("4", 1_700.0, 1_500_000),
        Location("5", 800.0, 1_200_000)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_location_list, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = LocationAdapter(locations)

        return view
    }
}