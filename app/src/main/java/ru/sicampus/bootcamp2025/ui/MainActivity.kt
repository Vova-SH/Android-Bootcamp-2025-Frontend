package ru.sicampus.bootcamp2025.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.FragmentLocationListBinding
import ru.sicampus.bootcamp2025.databinding.FragmentMainScreenBinding
import ru.sicampus.bootcamp2025.databinding.FragmentProfileBinding

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidBootcamp2025FrontendTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    var selectedItem by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_background),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            SelectedFragment(
                modifier = Modifier.weight(1f),
                selectedItem = selectedItem
            )

            BottomNavigationBar(
                selectedItem = selectedItem,
                onItemSelected = { index -> selectedItem = index }
            )
        }
    }
}

@Composable
fun SelectedFragment(
    modifier: Modifier = Modifier,
    selectedItem: Int
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        when (selectedItem) {
            0 -> FragmentLocationList()
            1 -> FragmentMainScreen()
            2 -> FragmentProfile()
            else -> FragmentLocationList()
        }
    }
}

@Composable
fun FragmentLocationList() {
    AndroidViewBinding(
        factory = FragmentLocationListBinding::inflate,
        modifier = Modifier.fillMaxSize()
    ) {
    }
}

@Composable
fun FragmentMainScreen() {
    AndroidViewBinding(
        factory = FragmentMainScreenBinding::inflate,
        modifier = Modifier.fillMaxSize()
    ) {
    }
}

@Composable
fun FragmentProfile() {
    AndroidViewBinding(
        factory = FragmentProfileBinding::inflate,
        modifier = Modifier.fillMaxSize()
    ) {
    }
}

@Composable
fun BottomNavigationBar(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {
    val items = listOf(
        BottomNavigationItem(
            icon = painterResource(id = R.drawable.ic_home_foreground),
            label = "Location List"
        ),
        BottomNavigationItem(
            icon = painterResource(id = R.drawable.ic_home_foreground),
            label = "Main Screen"
        ),
        BottomNavigationItem(
            icon = painterResource(id = R.drawable.ic_user_foreground),
            label = "Profile"
        )
    )

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp),
        containerColor = Color.White.copy(alpha = 0.9f),
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = item.icon,
                        contentDescription = item.label,
                        tint = Color(0xFF006400)
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        color = Color(0xFF006400)
                    )
                },
                selected = selectedItem == index,
                onClick = { onItemSelected(index) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF006400),
                    selectedTextColor = Color(0xFF006400),
                    indicatorColor = colorResource(R.color.buttonColor)
                )
            )
        }
    }
}

data class BottomNavigationItem(
    val icon: Painter,
    val label: String
)

@Composable
fun AndroidBootcamp2025FrontendTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = lightColorScheme(),
        typography = appTypography(),
        content = content
    )
}

@Composable
fun lightColorScheme() = lightColorScheme(
    primary = colorResource(id = R.color.colorPrimary),
    primaryContainer = colorResource(id = R.color.colorPrimaryDark),
    secondary = colorResource(id = R.color.colorAccent),
    secondaryContainer = colorResource(id = R.color.colorAccent),
    background = colorResource(id = R.color.white),
    surface = colorResource(id = R.color.white),
    onPrimary = colorResource(id = R.color.buttonTextColor),
    onSecondary = colorResource(id = R.color.textColor),
    onBackground = colorResource(id = R.color.textColor),
    onSurface = colorResource(id = R.color.textColor),
    error = colorResource(id = R.color.errorColor),
    onError = colorResource(id = R.color.white),
)

@Composable
fun appTypography() = Typography(
    titleMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        color = colorResource(id = R.color.textColor)
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = colorResource(id = R.color.textColorSecondary)
    )
)