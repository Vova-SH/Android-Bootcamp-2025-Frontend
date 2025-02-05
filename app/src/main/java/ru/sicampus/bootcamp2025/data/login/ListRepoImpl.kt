package ru.sicampus.bootcamp2025.data.login

import com.google.android.gms.maps.model.LatLng
import ru.sicampus.bootcamp2025.domain.list.ListEntity
import ru.sicampus.bootcamp2025.domain.list.ListRepo

class ListRepoImpl (
    private val userNetworkDataSource: ListNetworkDataSource
) : ListRepo {
    override suspend fun getUsers(): Result<List<ListEntity>> {
        return userNetworkDataSource.getUsers().map { listDto ->
            listDto.map { dto ->
                val id = dto.id
                val name = dto.name
                val description = dto.description
                val coordinates = dto.coordinates


                ListEntity(
                    id = id,
                    name = name,
                    description = description,
                    coordinates = coordinates
                )
            }
        }
    }

    private fun convertStringToLatLng(coordinates: String): LatLng {
        val parts = coordinates.split(":")
        if (parts.size != 2) {
            throw IllegalArgumentException("Invalid coordinates format: $coordinates")
        }
        val latitude = parts[0].toDoubleOrNull() ?: throw IllegalArgumentException("Invalid latitude value: ${parts[0]}")
        val longitude = parts[1].toDoubleOrNull() ?: throw IllegalArgumentException("Invalid longitude value: ${parts[1]}")
        return LatLng(latitude, longitude)
    }
}