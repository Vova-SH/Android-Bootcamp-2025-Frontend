package ru.sicampus.bootcamp2025.utils

import android.view.View

fun View.setVisibleOrGone(isVisibility: Boolean) {
    visibility = if (isVisibility) View.VISIBLE else View.GONE
}