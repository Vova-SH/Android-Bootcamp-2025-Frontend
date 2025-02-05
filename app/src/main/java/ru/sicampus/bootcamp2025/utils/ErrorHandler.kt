package ru.sicampus.bootcamp2025.utils

import android.content.Context
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.network.sockets.SocketTimeoutException
import java.io.IOException
import java.net.UnknownHostException
import ru.sicampus.bootcamp2025.R

fun Throwable?.toReadableMessage(context: Context): String? {
    if (this == null) return null

    return when (this) {
        is ClientRequestException -> when (response.status.value) {
            401 -> context.getString(R.string.error_unauthorized)
            403 -> context.getString(R.string.error_forbidden)
            404 -> context.getString(R.string.error_not_found)
            in 400..499 -> context.getString(R.string.error_client)
            else -> context.getString(R.string.error_network)
        }
        is ServerResponseException -> context.getString(R.string.error_server)
        is IOException -> when (this) {
            is SocketTimeoutException -> context.getString(R.string.error_timeout)
            is UnknownHostException -> context.getString(R.string.error_no_internet)
            else -> context.getString(R.string.error_network_general)
        }
        else -> context.getString(R.string.error_unknown)
    }
}