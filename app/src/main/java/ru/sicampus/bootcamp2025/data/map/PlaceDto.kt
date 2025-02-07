package ru.sicampus.bootcamp2025.data.map

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceDto(
    @SerialName("id")
    var id: Long,
    @SerialName("name")
    var name: String,
    @SerialName("pathToImage")
    var pathToImage: String,
    @SerialName("address")
    var address: String,
    @SerialName("information")
    var information: String,
    @SerialName("latLng")
    var latLng: String
) {
}