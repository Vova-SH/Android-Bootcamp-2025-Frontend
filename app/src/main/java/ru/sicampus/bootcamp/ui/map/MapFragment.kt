package ru.sicampus.bootcamp.ui.map

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import ru.sicampus.bootcamp.R
import ru.sicampus.bootcamp.Service.MapService
import ru.sicampus.bootcamp.databinding.MapBinding
import ru.sicampus.bootcamp.ui.auth.AuthViewModel
import ru.sicampus.bootcamp.ui.centersList.CentersListFragment
import ru.sicampus.bootcamp.ui.list.CenterListViewModel
import ru.sicampus.bootcamp.ui.list.ListFragment
import ru.sicampus.bootcamp.ui.list.ProfileFragment
import ru.sicampus.bootcamp.utils.collectWithLifecycle

class MapFragment : Fragment(R.layout.map), OnMapReadyCallback {
    private var _viewBinding: MapBinding? = null
    private val viewBinding: MapBinding get() = _viewBinding!!

    private val viewModel by viewModels<AuthViewModel> { AuthViewModel.Factory }
    private val viewModelMap by viewModels<CenterListViewModel> { CenterListViewModel.Factory }

    private lateinit var mapService: MapService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewBinding = MapBinding.bind(view)

        // Инициализация MapService
        mapService = MapService(requireContext(), viewModelMap)

        // Получаем SupportMapFragment и уведомляем, когда карта готова к использованию
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)

        viewBinding.listIc.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, CentersListFragment())
                .commitAllowingStateLoss()
        }
        viewBinding.profileIc.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, ProfileFragment())
                .commitAllowingStateLoss()}

        viewModel.action.collectWithLifecycle(this) { action ->
            when (action) {
                AuthViewModel.Action.GoToList -> {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.main, CentersListFragment())
                        .commitAllowingStateLoss()
                }
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        // Передаем googleMap в mapService
        mapService.onMapReady(googleMap)
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}
