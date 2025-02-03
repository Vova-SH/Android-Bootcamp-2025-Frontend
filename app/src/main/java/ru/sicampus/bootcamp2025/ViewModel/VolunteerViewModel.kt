package ru.sicampus.bootcamp2025.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.model.Volunteer
import ru.sicampus.bootcamp2025.repository.VolunteerRepository

class VolunteerViewModel(private val repository: VolunteerRepository) : ViewModel() {
    private val _volunteers = MutableLiveData<List<Volunteer>>()
    val volunteers: LiveData<List<Volunteer>> get() = _volunteers

    fun loadVolunteers() {
        viewModelScope.launch {
            val volunteersList = repository.getVolunteers()
            _volunteers.value = volunteersList ?: emptyList() // Обновляем LiveData
        }
    }
}