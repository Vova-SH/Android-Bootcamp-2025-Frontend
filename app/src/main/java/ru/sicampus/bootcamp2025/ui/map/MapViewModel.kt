package ru.sicampus.bootcamp2025.ui.map

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.data.auth.storage.AuthStorageDataSource
import ru.sicampus.bootcamp2025.data.map.MapNetworkDataSource
import ru.sicampus.bootcamp2025.data.map.MapRepoImpl
import ru.sicampus.bootcamp2025.data.profile.PersonUpdateDto
import ru.sicampus.bootcamp2025.data.profile.ProfileNetworkDataSource
import ru.sicampus.bootcamp2025.data.profile.ProfileRepoImpl
import ru.sicampus.bootcamp2025.domain.map.ChangeDepartmentUserCase
import ru.sicampus.bootcamp2025.domain.map.DepartmentEntity
import ru.sicampus.bootcamp2025.domain.map.GetPlaceByNameUserCase
import ru.sicampus.bootcamp2025.domain.map.GetPlacesUserCase


class MapViewModel(
    private val getPlacesUserCase: GetPlacesUserCase,
    private val getPlaceByNameUserCase: GetPlaceByNameUserCase,
    private val changeDepartmentUserCase: ChangeDepartmentUserCase,
) : ViewModel() {
//    private val _state = MutableStateFlow<State>(State.Loading)
//    public val state = _state.asStateFlow()
    private val _placesLiveData = MutableLiveData<List<DepartmentEntity>>()
    val placesLiveData: LiveData<List<DepartmentEntity>> get() = _placesLiveData
    private val _selectedDepartment = MutableLiveData<DepartmentEntity?>()
    val selectedDepartment: LiveData<DepartmentEntity?> get() = _selectedDepartment


    fun getPlaces() {
        viewModelScope.launch {
            getPlacesUserCase.invoke().onSuccess { places ->
                _placesLiveData.value = places
            }.onFailure { throwable ->
                Log.e("MyViewModel", "Error fetching places: ${throwable.message}")
            }
        }
    }

    fun getDepartmentByName(name: String?) {
        viewModelScope.launch {
            getPlaceByNameUserCase.getPlaceByName(name.orEmpty()).fold(
                onSuccess = { place ->
                    _selectedDepartment.value = place
                },
                onFailure = { throwable ->
                    _selectedDepartment.value = null
                    Log.e("MyViewModel", "Error fetching place by name: ${throwable.message}")
                }
            )
        }
    }
    fun changeDepartmentAttach(departmentName: String) {
        viewModelScope.launch {
            changeDepartmentUserCase.invoke(
                PersonUpdateDto(
                    id = null,
                    name = null,
                    login = null,
                    email = null,
                    info = null,
                    phone = null,
                    departmentName = departmentName,
                )
            ).fold(
                onSuccess = { Log.d("MapViewModel", "onSuccess") },
                onFailure = { Log.d("MapViewModel", "onFailure") },
            )
        }
    }


    companion object {
        var Factory: ViewModelProvider.Factory = object: ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val mapRepo = MapRepoImpl(
                    mapNetworkDataSource = MapNetworkDataSource(),
                    authStorageDataSource = AuthStorageDataSource,
                )
                return MapViewModel(
                    getPlacesUserCase = GetPlacesUserCase(
                        mapRepo
                    ),
                    getPlaceByNameUserCase = GetPlaceByNameUserCase(
                        mapRepo
                    ),
                    changeDepartmentUserCase = ChangeDepartmentUserCase(
                        mapRepo
                    ),
                ) as T
            }
        }
    }
//    sealed interface State {
//        data object Loading : State
//        data class Show() : State
//    }
}
