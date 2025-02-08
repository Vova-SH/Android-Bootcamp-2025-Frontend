package ru.sicampus.bootcamp2025.ui.mainscreen.allcenters

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.domain.entities.CenterMapEntity

class MapService(
    private val centers: List<CenterMapEntity>,
    private val onClick: (centerId: Int) -> Unit
) : OnMapReadyCallback, OnMarkerClickListener {
    private val markers: HashMap<String, Int> = HashMap()

    override fun onMapReady(map: GoogleMap) {
        map.setOnMarkerClickListener(this)

        centers.forEach { center ->
            val marker = map.addMarker(
                MarkerOptions()
                    .title(center.name)
                    .position(LatLng(center.latitude, center.longitude))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marks))
            )
            if (marker != null)
                markers[marker.id] = center.id
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        val clickedCenter = markers[marker.id]
        if (clickedCenter != null)
            onClick(clickedCenter)
        return false
    }
}