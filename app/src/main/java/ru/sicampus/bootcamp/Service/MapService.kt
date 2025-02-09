package ru.sicampus.bootcamp.Service

import android.content.Context
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp.data.auth.AuthStorageDataSource
import ru.sicampus.bootcamp.data.list.CentersRepoImpl
import ru.sicampus.bootcamp.domain.list.CentersEntity
import ru.sicampus.bootcamp.ui.list.CenterListViewModel

class MapService(
    private val context: Context,
    private val centerListViewModel: CenterListViewModel
): OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener {

    override fun onMapReady(googleMap: GoogleMap) {


        googleMap.setOnMapClickListener(this)
        googleMap.setOnMapLongClickListener(this)
        googleMap.setOnMarkerClickListener(this)

        CoroutineScope(Dispatchers.Main).launch {
            centerListViewModel.state.collect { state ->
                when (state) {
                    is CenterListViewModel.State.Show -> {
                        val centers = state.items
                        for (center in centers) {
                            googleMap.addMarker(
                                MarkerOptions()
                                    .title(center.name)
                                    .position(LatLng(center.lon.toDouble(), center.lat.toDouble()))
                            )
                        }
                    }
                    is CenterListViewModel.State.Error -> {
                        Toast.makeText(context, "Error: ${state.text}", Toast.LENGTH_SHORT).show()
                    }
                    CenterListViewModel.State.Loading -> {
                    }

                    else -> {}
                }
            }
        }
    }

    override fun onMapClick(latLng: LatLng) {
        Toast.makeText(context, "Coord:" + latLng.latitude + " " + latLng.longitude, Toast.LENGTH_SHORT).show()
    }

    override fun onMapLongClick(latLng: LatLng) {
        Toast.makeText(context, "LONG Coord:" + latLng.latitude + " " + latLng.longitude, Toast.LENGTH_SHORT).show()
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        return false
    }

}
