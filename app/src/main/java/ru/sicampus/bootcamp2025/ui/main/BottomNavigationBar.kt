package ru.sicampus.bootcamp2025.ui.main

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.compose.foundation.border
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.sicampus.bootcamp2025.ui.theme.AppTheme

class BottomNavigationBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    initialSelectedIndex: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val composeView = ComposeView(context)

    private var selectedIndex by mutableIntStateOf(initialSelectedIndex)
    private var items: List<NavItem> = emptyList()
    private var onItemSelected: ((Int) -> Unit)? = null

    init {
        addView(composeView)
        setupComposeContent()
    }

    private fun setupComposeContent() {
        composeView.setContent {
            AppTheme {
                NavigationBottomBarContent()
            }
        }
    }

    fun setup(
        items: List<NavItem>,
        onItemSelected: (Int) -> Unit
    ) {
        this.items = items
        this.onItemSelected = onItemSelected
        updateComposeContent()
    }

    fun updateSelectedIndex(newIndex: Int) {
        selectedIndex = newIndex
        updateComposeContent()
    }

    private fun updateComposeContent() {
        composeView.invalidate()
    }

    @Composable
    private fun NavigationBottomBarContent() {
        val darkGreen = Color(0xFF2E7D32)
        val white = Color.White

        if (items.isNotEmpty()) {
            NavigationBar(
                containerColor = white,
                contentColor = darkGreen
            ) {
                items.forEachIndexed { index, item ->
                    if (item.showInNav) {
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painter = painterResource(id = item.iconResId),
                                    contentDescription = item.title,
                                    tint = darkGreen
                                )
                            },
                            label = {
                                Text(
                                    text = item.title,
                                    color = darkGreen
                                )
                            },
                            selected = selectedIndex == index,
                            onClick = {
                                selectedIndex = index
                                onItemSelected?.invoke(index)
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = darkGreen,
                                unselectedIconColor = darkGreen,
                                selectedTextColor = darkGreen,
                                unselectedTextColor = darkGreen,
                                indicatorColor = white.copy(alpha = 0.8f)
                            ),
                            modifier = Modifier
                                .clip(MaterialTheme.shapes.medium)
                                .border(
                                    width = 4.dp,
                                    color = if (selectedIndex == index) darkGreen else Color.Transparent,
                                    shape = MaterialTheme.shapes.medium
                                )
                        )
                    }
                }
            }
        }
    }

    data class NavItem(
        val title: String,
        val iconResId: Int,
        val showInNav: Boolean = true
    )
}