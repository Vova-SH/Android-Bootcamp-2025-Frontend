package ru.sicampus.bootcamp2025.ui.mainscreen.allcenters

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.ViewCentersFragmentBinding
import ru.sicampus.bootcamp2025.ui.utils.collectWithLifecycle
import ru.sicampus.bootcamp2025.ui.utils.getColor
import ru.sicampus.bootcamp2025.ui.utils.visibleOrGone

class AllCentersFragment : Fragment(R.layout.view_centers_fragment) {
    private var _binding: ViewCentersFragmentBinding? = null
    private val binding: ViewCentersFragmentBinding get() = _binding!!

    private val viewModel by viewModels<AllCentersViewModel> { AllCentersViewModel.Factory }

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = ViewCentersFragmentBinding.bind(view)
        activateListSwitcher()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        val adapter = CenterListAdapter(getCurrentLocation())
        binding.content.adapter = adapter

        binding.mapSwitcher.setOnClickListener { activateMapSwitcher() }
        binding.listSwitcher.setOnClickListener { activateListSwitcher() }
        binding.refresh.setOnRefreshListener { adapter.refresh() }

        viewModel.listState.collectWithLifecycle(this) { listState ->
            adapter.submitData(listState)
        }

        adapter.loadStateFlow.collectWithLifecycle(this) { data ->
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
        binding.map.visibility = visibility
    }

    private fun setUpListSwitcher(backgroundColorId: Int, textColorId: Int, visibility: Int) {
        binding.listSwitcher.setBackgroundColor(getColor(backgroundColorId, requireContext()))
        binding.listSwitcher.setTextColor(getColor(textColorId, requireContext()))
        binding.content.visibility = visibility
    }

    private fun activateMapSwitcher() {
        setUpMapSwitcher(R.color.blue, R.color.gray, View.VISIBLE)
        setUpListSwitcher(R.color.gray, R.color.black, View.GONE)
    }

    private fun activateListSwitcher() {
        setUpMapSwitcher(R.color.gray, R.color.black, View.GONE)
        setUpListSwitcher(R.color.blue, R.color.gray, View.VISIBLE)
    }

    private fun getCurrentLocation(): Pair<Double, Double> {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Add permissions request
        }

        val defaultLatAndLong: Pair<Double, Double> = Pair(100.0, 100.0)
        var latAndLong: Pair<Double, Double> = defaultLatAndLong

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                latAndLong = Pair(location.latitude, location.longitude)
            }

        return latAndLong
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}