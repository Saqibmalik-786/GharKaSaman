
package com.example.gharkasaman

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.gharkasaman.ui.screens.HomeScreen
import com.example.gharkasaman.ui.screens.MyListScreen
import com.example.gharkasaman.ui.screens.SettingsScreen
import com.example.gharkasaman.ui.theme.CreamBackground
import com.example.gharkasaman.ui.components.GroceryNavigation
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = CreamBackground
                ) {
                    GroceryApp()
                }
            }
        }
    }
}

@Composable
fun GroceryApp() {
    var selectedTab by remember { mutableIntStateOf(0) }

    GroceryNavigation(
        selectedTab = selectedTab,
        onTabSelected = { selectedTab = it }
    ) {
        when (selectedTab) {
            0 -> HomeScreen()
            1 -> MyListScreen()
            2 -> SettingsScreen()
        }
    }
}
