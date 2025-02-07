package ru.sicampus.bootcamp2025.data.map

import com.google.android.gms.maps.model.LatLng
import ru.sicampus.bootcamp2025.data.auth.AuthStorageDataSource
import ru.sicampus.bootcamp2025.domain.map.DepartmentEntity
import ru.sicampus.bootcamp2025.domain.map.MapRepo
import ru.sicampus.bootcamp2025.domain.map.PlaceEntity

class MapRepoImpl(
    private val mapNetworkDataSource: MapNetworkDataSource,
    private val authStorageDataSource: AuthStorageDataSource
) : MapRepo {
    override suspend fun getPlaces(): Result<List<DepartmentEntity>> {
        val token = authStorageDataSource.token
            ?: return Result.failure(IllegalStateException("token is null"))
        return mapNetworkDataSource.getPlaces(token).map { departmentlist ->
            departmentlist.content?.mapNotNull { department ->
                DepartmentEntity(
                    name = department.name,
                    place = PlaceEntity(
                        id = department.place.id,
                        name = department.place.name,
                        pathToImage = department.place.pathToImage,
                        address = department.place.address,
                        information = department.place.information,
                        latLng = LatLng(
                            department.place.latLng.split(" ")[0].toDouble(),
                            department.place.latLng.split(" ")[1].toDouble()
                        )
                    )
                )
            } ?: return Result.failure(IllegalStateException("list parse error"))
        }
    }

    override suspend fun getPlaceByName(name: String): Result<DepartmentEntity> {
        val token = authStorageDataSource.token
            ?: return Result.failure(IllegalStateException("token is null"))
        return mapNetworkDataSource.getPlaceByName(name, token).mapCatching {department ->
            DepartmentEntity(
                name = department.name,
                place = PlaceEntity(
                    id = department.place.id,
                    name = department.place.name,
                    pathToImage = department.place.pathToImage,
                    address = department.place.address,
                    information = department.place.information,
                    latLng = LatLng(
                        department.place.latLng.split(" ")[0].toDouble(),
                        department.place.latLng.split(" ")[1].toDouble()
                    )
                )
            )
        }
    }

}