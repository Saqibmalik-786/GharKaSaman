
package com.example.gharkasaman.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gharkasaman.ui.theme.AppWhite
import com.example.gharkasaman.ui.theme.PrimaryGreen
import com.example.gharkasaman.ui.theme.SecondaryText

@Composable
fun GroceryNavigation(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Screen content appears above the bottom navigation
        Box(
            modifier = Modifier.weight(1f)
        ) {
            content()
        }

        // Fixed bottom navigation
        NavigationBar(
            containerColor = AppWhite,
            contentColor = PrimaryGreen,
            tonalElevation = 8.dp
        ) {
            NavigationBarItem(
                selected = selectedTab == 0,
                onClick = { onTabSelected(0) },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home"
                    )
                },
                label = {
                    Text("Home", fontSize = 12.sp)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = PrimaryGreen,
                    selectedTextColor = PrimaryGreen,
                    indicatorColor = Color(0xFFE4EEE5),
                    unselectedIconColor = SecondaryText,
                    unselectedTextColor = SecondaryText
                )
            )

            NavigationBarItem(
                selected = selectedTab == 1,
                onClick = { onTabSelected(1) },
                icon = {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = "My List"
                    )
                },
                label = {
                    Text("My List", fontSize = 12.sp)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = PrimaryGreen,
                    selectedTextColor = PrimaryGreen,
                    indicatorColor = Color(0xFFE4EEE5),
                    unselectedIconColor = SecondaryText,
                    unselectedTextColor = SecondaryText
                )
            )

            NavigationBarItem(
                selected = selectedTab == 2,
                onClick = { onTabSelected(2) },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings"
                    )
                },
                label = {
                    Text("Settings", fontSize = 12.sp)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = PrimaryGreen,
                    selectedTextColor = PrimaryGreen,
                    indicatorColor = Color(0xFFE4EEE5),
                    unselectedIconColor = SecondaryText,
                    unselectedTextColor = SecondaryText
                )
            )
        }
    }
}
