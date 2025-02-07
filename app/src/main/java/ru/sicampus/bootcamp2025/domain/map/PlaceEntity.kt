package ru.sicampus.bootcamp2025.domain.map

import com.google.android.gms.maps.model.LatLng


data class PlaceEntity(
    var id: Long,
    var name: String,
    var pathToImage: String,
    var address: String,
    var information: String,
    var latLng: LatLng
) {
}