package ru.sicampus.bootcamp2025.domain.map

import android.util.Log
import ru.sicampus.bootcamp2025.domain.list.UserEntity

class GetPlacesUserCase(
    private val mapRepo: MapRepo
) {
   operator suspend fun invoke(): Result<List<DepartmentEntity>> {
       Log.d("zzz", "test4")
       return mapRepo.getPlaces()
   }
}