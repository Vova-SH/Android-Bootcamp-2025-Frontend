package ru.sicampus.bootcamp2025.ui.mainscreen.allcenters

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.SupportMapFragment
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.ViewCentersFragmentBinding
import ru.sicampus.bootcamp2025.ui.mainscreen.centerinfo.CenterInfoFragment
import ru.sicampus.bootcamp2025.ui.utils.collectWithLifecycle
import ru.sicampus.bootcamp2025.ui.utils.getColor
import ru.sicampus.bootcamp2025.ui.utils.visibleOrGone

class AllCentersFragment : Fragment(R.layout.view_centers_fragment) {
    private var _binding: ViewCentersFragmentBinding? = null
    private val binding: ViewCentersFragmentBinding get() = _binding!!

    private var _fusedLocationProviderClient: FusedLocationProviderClient? = null
    private val fusedLocationProviderClient: FusedLocationProviderClient
        get() = _fusedLocationProviderClient!!

    private var _mapFragment: SupportMapFragment? = null
    private val mapFragment: SupportMapFragment get() = _mapFragment!!

    private var currentLocation: Pair<Double, Double> = Pair(100.0, 100.0)

    private val viewModel by viewModels<AllCentersViewModel> { AllCentersViewModel.Factory }
    private var permissionsGranted: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())
        requestPermissions()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = ViewCentersFragmentBinding.bind(view)
        activateListSwitcher()

        if (permissionsGranted) getCurrentLocation()
        val adapter = CenterListAdapter(currentLocation) { centerId: Int, centerName: String ->
            CenterInfoFragment(centerId).show(requireActivity().supportFragmentManager, centerName)
        }
        binding.content.adapter = adapter

        _mapFragment = binding.map.getFragment<SupportMapFragment>()

        binding.mapSwitcher.setOnClickListener { activateMapSwitcher() }
        binding.listSwitcher.setOnClickListener { activateListSwitcher() }
        binding.refresh.setOnRefreshListener { adapter.refresh() }

        viewModel.state.collectWithLifecycle(this) { state ->
            when (state) {
                is AllCentersViewModel.State.Error -> binding.error.text = state.error
                AllCentersViewModel.State.Loading -> Unit
                is AllCentersViewModel.State.Show ->
                    mapFragment.getMapAsync(MapService(state.content) { centerId: Int ->
                        CenterInfoFragment(centerId).show(
                            requireActivity().supportFragmentManager,
                            "Center Info"
                        )
                    }
                    )
            }
        }

        viewModel.listState.collectWithLifecycle(this) { listState ->
            adapter.submitData(listState)
        }

        adapter.loadStateFlow.collectWithLifecycle(this) { data ->
            if (permissionsGranted) getCurrentLocation()
            adapter.updateLocation(currentLocation)
            val state = data.refresh
            binding.refresh.isRefreshing = state is LoadState.Loading
            binding.error.visibility = visibleOrGone(state is LoadState.Error)

            if (state is LoadState.Error) {
                binding.error.text = state.error.toString()
            }
        }

    }

    private fun setUpMapSwitcher(backgroundColorId: Int, textColorId: Int, visibility: Int) {
        binding.mapSwitcher.setBackgroundColor(getColor(backgroundColorId, requireContext()))
        binding.mapSwitcher.setTextColor(getColor(textColorId, requireContext()))
        binding.mapWrapper.visibility = visibility
    }

    private fun setUpListSwitcher(backgroundColorId: Int, textColorId: Int, visibility: Int) {
        binding.listSwitcher.setBackgroundColor(getColor(backgroundColorId, requireContext()))
        binding.listSwitcher.setTextColor(getColor(textColorId, requireContext()))
        binding.content.visibility = visibility
    }

    private fun activateMapSwitcher() {
        setUpMapSwitcher(R.color.blue, R.color.gray, View.VISIBLE)
        setUpListSwitcher(R.color.gray, R.color.black, View.GONE)
        binding.refresh.isEnabled = false
        viewModel.onMapSelected()
    }

    private fun activateListSwitcher() {
        setUpMapSwitcher(R.color.gray, R.color.black, View.GONE)
        setUpListSwitcher(R.color.blue, R.color.gray, View.VISIBLE)
        binding.refresh.isEnabled = true
    }

    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) requestPermissions()

        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location ->
                currentLocation = Pair(location.latitude, location.longitude)
            }
    }

    private fun requestPermissions() {
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> Unit
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> Unit
                else -> {
                    binding.mapWrapper.visibility = View.GONE
                }
            }
        }

        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}