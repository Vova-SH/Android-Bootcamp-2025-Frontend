package ru.sicampus.bootcamp2025.ui.map

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.domain.map.DepartmentEntity
import ru.sicampus.bootcamp2025.domain.map.PlaceEntity


class MapFragment() : Fragment(R.layout.fragment_map),
    OnMapReadyCallback,
    GoogleMap.OnMapClickListener,
    GoogleMap.OnMapLongClickListener,
    GoogleMap.OnMarkerClickListener {

    private lateinit var googleMap: GoogleMap
    private val viewModel: MapViewModel by viewModels() { MapViewModel.Factory}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.google_map) as? SupportMapFragment
        if (mapFragment == null) {
            Log.e("MapFragment", "MapFragment is null!")
        } else {
            mapFragment.getMapAsync(this)
        }

        viewModel.placesLiveData.observe(viewLifecycleOwner) { departments ->

            if (::googleMap.isInitialized) {
                for (department in departments) {
                    googleMap.addMarker(MarkerOptions().position(department.place.latLng).title(department.name))
                }
            }
        }
        viewModel.selectedDepartment.observe(viewLifecycleOwner) { department ->
            if (department != null) {
                showPlaceDetails(department)
            } else {
                Toast.makeText(requireContext(), "Place not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.setOnMapClickListener(this)
        googleMap.setOnMapLongClickListener(this)
        googleMap.setOnMarkerClickListener(this)

        viewModel.getPlaces()

    }

    override fun onMapClick(latLng: LatLng) {
        Toast.makeText(requireContext(), "Coords: ${latLng.latitude} ${latLng.longitude}", Toast.LENGTH_SHORT).show()
    }

    override fun onMapLongClick(latLng: LatLng) {
        Toast.makeText(requireContext(), "LONG Coords: ${latLng.latitude} ${latLng.longitude}", Toast.LENGTH_SHORT).show()
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        viewModel.getDepartmentByName(marker.title)
        return false
    }

    private fun showPlaceDetails(department: DepartmentEntity) {
        val place = department.place
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.bottom_sheet_dialog)
        dialog.window?.apply {
            setBackgroundDrawableResource(android.R.color.transparent)
        }
        dialog.show()
        Log.d("MapFragment", "${place.pathToImage}")
        dialog.findViewById<ImageView>(R.id.image)?.let {
            Glide.with(this)
                .load(place.pathToImage) // TODO()
                .placeholder(R.drawable.ic_photo)
                .error(R.drawable.ic_back)
                .into(it)
        }
//        dialog.findViewById<ImageView>(R.id.image)?.let {
//            imageService.setImage(it, place.pathToImage)
//        }
        dialog.findViewById<TextView>(R.id.name)?.text = place.name
        dialog.findViewById<TextView>(R.id.address)?.text = place.address
        dialog.findViewById<TextView>(R.id.description)?.text = place.information
        dialog.findViewById<TextView>(R.id.attach)?.setOnClickListener {
            viewModel.changeDepartmentAttach(department.name)
        }
    }

}
