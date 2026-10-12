
package com.example.gharkasaman.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BakeryDining
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gharkasaman.ui.theme.*

data class GroceryCategory(
    val name: String,
    val icon: ImageVector
)

data class GroceryItem(
    val name: String,
    val category: String
)

@Composable
fun HomeScreen() {
    var searchText by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var quantities by remember { mutableStateOf(mapOf<String, Int>()) }
    var addedItems by remember { mutableStateOf(setOf<String>()) }

    val categories = listOf(
        GroceryCategory("Dairy & Eggs", Icons.Default.ShoppingBasket),
        GroceryCategory("Vegetables", Icons.Default.Eco),
        GroceryCategory("Fruits", Icons.Default.LocalFlorist),
        GroceryCategory("Staples", Icons.Default.ShoppingBasket),
        GroceryCategory("Bakery", Icons.Default.BakeryDining),
        GroceryCategory("Household", Icons.Default.CleaningServices)
    )

    val groceryItems = listOf(
        GroceryItem("Milk", "Dairy & Eggs"),
        GroceryItem("Eggs", "Dairy & Eggs"),
        GroceryItem("Butter", "Dairy & Eggs"),
        GroceryItem("Cheese", "Dairy & Eggs"),
        GroceryItem("Yogurt", "Dairy & Eggs"),

        GroceryItem("Potato", "Vegetables"),
        GroceryItem("Onion", "Vegetables"),
        GroceryItem("Tomato", "Vegetables"),
        GroceryItem("Carrot", "Vegetables"),
        GroceryItem("Spinach", "Vegetables"),

        GroceryItem("Apple", "Fruits"),
        GroceryItem("Banana", "Fruits"),
        GroceryItem("Orange", "Fruits"),
        GroceryItem("Mango", "Fruits"),
        GroceryItem("Grapes", "Fruits"),

        GroceryItem("Rice", "Staples"),
        GroceryItem("Flour", "Staples"),
        GroceryItem("Sugar", "Staples"),
        GroceryItem("Lentils", "Staples"),
        GroceryItem("Cooking Oil", "Staples"),

        GroceryItem("Bread", "Bakery"),
        GroceryItem("Buns", "Bakery"),
        GroceryItem("Rusk", "Bakery"),
        GroceryItem("Cake", "Bakery"),

        GroceryItem("Dishwashing Liquid", "Household"),
        GroceryItem("Laundry Detergent", "Household"),
        GroceryItem("Tissue Paper", "Household"),
        GroceryItem("Garbage Bags", "Household")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CreamBackground)
            .padding(horizontal = 22.dp)
            .padding(top = 60.dp)
    ) {
        // Fixed header
        Text(
            text = "Ghar Ka Saman",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = DarkGreen
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Your daily groceries, simply organized.",
            fontSize = 15.sp,
            color = SecondaryText
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Fixed search bar
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp),
            placeholder = {
                Text(
                    text = "Search your groceries...",
                    fontSize = 14.sp
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryGreen,
                unfocusedBorderColor = Color(0xFFE5E1D9),
                focusedContainerColor = AppWhite,
                unfocusedContainerColor = AppWhite
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Only this middle section changes
        if (selectedCategory == null) {

            Text(
                text = "Shop by Category",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = DarkGreen
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Find what you need",
                fontSize = 14.sp,
                color = SecondaryText
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                items(categories) { category ->
                    CategoryCard(
                        category = category,
                        onClick = {
                            selectedCategory = category.name
                            searchText = ""
                        }
                    )
                }
            }

        } else {

            // Selected category screen
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        selectedCategory = null
                        searchText = ""
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back to categories",
                        tint = PrimaryGreen
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = selectedCategory!!,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkGreen
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Choose your groceries",
                fontSize = 14.sp,
                color = SecondaryText
            )

            Spacer(modifier = Modifier.height(14.dp))

            val filteredItems = groceryItems.filter {
                it.category == selectedCategory &&
                        it.name.contains(searchText, ignoreCase = true)
            }

            if (filteredItems.isEmpty()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No items found.",
                        color = SecondaryText
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 20.dp)
                ) {
                    items(filteredItems) { item ->
                        GroceryItemCard(
                            item = item,
                            quantity = quantities[item.name] ?: 1,
                            isAdded = item.name in addedItems,
                            onQuantityChange = { newQuantity ->
                                quantities = quantities + (item.name to newQuantity)
                            },
                            onAddClick = {
                                addedItems = addedItems + item.name
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryCard(
    category: GroceryCategory,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppWhite
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Icon(
                imageVector = category.icon,
                contentDescription = category.name,
                tint = PrimaryGreen,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = category.name,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = DarkGreen
            )
        }
    }
}

@Composable
fun GroceryItemCard(
    item: GroceryItem,
    quantity: Int,
    isAdded: Boolean,
    onQuantityChange: (Int) -> Unit,
    onAddClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppWhite
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = item.name,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = DarkGreen
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            if (quantity > 1) {
                                onQuantityChange(quantity - 1)
                            }
                        },
                        enabled = quantity > 1,
                        modifier = Modifier.size(34.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = "Decrease quantity",
                            tint = PrimaryGreen
                        )
                    }

                    Text(
                        text = quantity.toString(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkGreen
                    )

                    IconButton(
                        onClick = {
                            onQuantityChange(quantity + 1)
                        },
                        modifier = Modifier.size(34.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Increase quantity",
                            tint = PrimaryGreen
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = onAddClick,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isAdded) {
                            DarkGreen
                        } else {
                            PrimaryGreen
                        }
                    ),
                    contentPadding = PaddingValues(
                        horizontal = 12.dp,
                        vertical = 8.dp
                    )
                ) {
                    Text(
                        text = if (isAdded) "Added" else "Add to List",
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}
