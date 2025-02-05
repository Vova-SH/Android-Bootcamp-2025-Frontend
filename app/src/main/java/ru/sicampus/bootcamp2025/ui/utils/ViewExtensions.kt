package ru.sicampus.bootcamp2025.ui.utils

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat

fun visibleOrGone(condition: Boolean): Int {
    return if (condition) View.VISIBLE else View.GONE
}

fun getColor(colorId: Int, context: Context) : Int {
    return ContextCompat.getColor(context, colorId)
}

fun getText(textId: Int, context: Context) : String {
    return ContextCompat.getString(context, textId)
}