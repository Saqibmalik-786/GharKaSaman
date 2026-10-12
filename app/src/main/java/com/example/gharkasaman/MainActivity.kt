
package com.example.gharkasaman

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.gharkasaman.ui.components.GroceryNavigation
import com.example.gharkasaman.ui.screens.HomeScreen
import com.example.gharkasaman.ui.screens.MyListScreen
import com.example.gharkasaman.ui.screens.SettingsScreen
import com.example.gharkasaman.ui.theme.CreamBackground

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

    // Shared grocery data: item name -> quantity
    var groceryList by remember {
        mutableStateOf<Map<String, Int>>(emptyMap())
    }

    var purchasedItems by remember {
        mutableStateOf<Set<String>>(emptySet())
    }

    GroceryNavigation(
        selectedTab = selectedTab,
        onTabSelected = { selectedTab = it }
    ) {
        when (selectedTab) {
            0 -> HomeScreen(
                addedItems = groceryList.keys,
                onAddItem = { name, quantity ->
                    groceryList = groceryList + (name to quantity)
                }
            )

            1 -> MyListScreen(
                groceryList = groceryList,
                purchasedItems = purchasedItems,
                onTogglePurchased = { name ->
                    purchasedItems = if (name in purchasedItems) {
                        purchasedItems - name
                    } else {
                        purchasedItems + name
                    }
                },
                onRemoveItem = { name ->
                    groceryList = groceryList - name
                    purchasedItems = purchasedItems - name
                }
            )

            2 -> SettingsScreen()
        }
    }
}
