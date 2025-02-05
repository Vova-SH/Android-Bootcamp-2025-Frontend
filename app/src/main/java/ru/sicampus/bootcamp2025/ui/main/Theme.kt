package ru.sicampus.bootcamp2025.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import ru.sicampus.bootcamp2025.R

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF00AA2E),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF00641B),
    onPrimaryContainer = Color(0xFFFFFFFF),
    secondary = Color(0xFFFF5722),
    onSecondary = Color(0xFFFFFFFF),
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF000000),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF000000),
    error = Color(0xFFFF5722),
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colors = LightColorScheme.copy(
        primary = Color(ContextCompat.getColor(context, R.color.colorPrimary)),
        primaryContainer = Color(ContextCompat.getColor(context, R.color.colorPrimaryDark)),
        secondary = Color(ContextCompat.getColor(context, R.color.colorAccent)),
        error = Color(ContextCompat.getColor(context, R.color.errorColor))
    )

    MaterialTheme(
        colorScheme = colors,
        typography = MaterialTheme.typography,
        content = content
    )
}