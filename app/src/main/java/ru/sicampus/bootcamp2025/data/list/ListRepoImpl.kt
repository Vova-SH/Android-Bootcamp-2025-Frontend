package ru.sicampus.bootcamp2025.data.list

import com.google.android.gms.maps.model.LatLng
import ru.sicampus.bootcamp2025.domain.list.ListEntity
import ru.sicampus.bootcamp2025.domain.list.ListRepo

fun String.toLatLng(): LatLng {
    val parts = this.split(":")
    require(parts.size == 2) { "Invalid coordinates format. Expected 'latitude:longitude'." }
    val latitude = parts[0].toDoubleOrNull() ?: throw IllegalArgumentException("Invalid latitude value")
    val longitude = parts[1].toDoubleOrNull() ?: throw IllegalArgumentException("Invalid longitude value")
    return LatLng(latitude, longitude)
}

class ListRepoImpl(
    private val userNetworkDataSource: UserNetworkDataSource
) : ListRepo {
    override suspend fun getUsers(
        pageNum: Int,
        pageSize: Int,
    ): Result<List<ListEntity>> {
        return userNetworkDataSource.getUsers(
            pageNum = pageNum,
            pageSize = pageSize
        ).map { pagingDto ->
            pagingDto.content?.map { dto ->
                ListEntity(
                    id = dto.id,
                    name = dto.name,
                    description = dto.description,
                    coordinates = dto.coordinates.toLatLng()
                )
            } ?: return Result.failure(IllegalStateException("List parse error"))
        }
    }
}