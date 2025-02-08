package ru.sicampus.bootcamp2025.ui.centerList

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.motion.widget.Debug.getLocation
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.location.LocationServices
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.FragmentCenterListBinding
import ru.sicampus.bootcamp2025.utils.collectWithLifecycle

class CenterListFragment : Fragment(R.layout.fragment_center_list) {
    private var _viewBinding: FragmentCenterListBinding? = null
    private val viewBinding: FragmentCenterListBinding get() = _viewBinding!!

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            getLocation()
        } else {
            Toast.makeText(requireContext(), "Location permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    private val viewModel by viewModels<CenterListViewModel> { CenterListViewModel.Factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _viewBinding = FragmentCenterListBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)


        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Разрешение уже предоставлено, запрашиваем местоположение
            getLocation()
        } else {
            // Запрашиваем разрешение
            locationPermissionRequest.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }



        viewBinding.refresh.setOnClickListener{ viewModel.clickRefresh()}

        val adapter = CenterAdapter()
        viewBinding.centerList.adapter = adapter

        viewModel.state.collectWithLifecycle(this) { state ->

            viewBinding.error.visibility = if (state is CenterListViewModel.State.Error) View.VISIBLE else View.GONE
            viewBinding.loading.visibility = if (state is CenterListViewModel.State.Loading) View.VISIBLE else View.GONE
            viewBinding.centerList.visibility = if (state is CenterListViewModel.State.Show) View.VISIBLE else View.GONE

            when(state) {
                is CenterListViewModel.State.Loading -> Unit
                is CenterListViewModel.State.Show -> {
                    Log.d("itemsInFragment", "${state.items.toString()}")
                    adapter.submitList(state.items)
                }
                is CenterListViewModel.State.Error -> {
                    viewBinding.errorText.text = state.text
                }
            }

        }


    }


    private fun getLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        // Местоположение получено, отправляем его в ViewModel
                        val latitude = location.latitude
                        val longitude = location.longitude
                        viewModel.setLocation(latitude, longitude)
                    } else {
                        Toast.makeText(requireContext(), "Location not available", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(requireContext(), "Failed to get location: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}
