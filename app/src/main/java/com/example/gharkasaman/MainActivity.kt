
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
    var showDuplicateDialog by remember {
        mutableStateOf(false)
    }

    var pendingItem by remember {
        mutableStateOf<Pair<String, Int>?>(null)
    }

    GroceryNavigation(
        selectedTab = selectedTab,
        onTabSelected = { selectedTab = it }
    ) {
        when (selectedTab) {
            0 -> HomeScreen(
                addedItems = groceryList.keys,
                onAddItem = { name, quantity ->
                    if (name in groceryList) {
                        pendingItem = name to quantity
                        showDuplicateDialog = true
                    } else {
                        groceryList = groceryList + (name to quantity)
                    }
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
    if (showDuplicateDialog && pendingItem != null) {
        AlertDialog(
            onDismissRequest = {
                showDuplicateDialog = false
                pendingItem = null
            },
            title = {
                Text("Item Already Added")
            },
            text = {
                Text(
                    "${pendingItem!!.first} is already in your grocery list. " +
                            "Do you want to update its quantity?"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val item = pendingItem!!
                        groceryList = groceryList + (item.first to item.second)

                        showDuplicateDialog = false
                        pendingItem = null
                    }
                ) {
                    Text("Update Quantity")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDuplicateDialog = false
                        pendingItem = null
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}
