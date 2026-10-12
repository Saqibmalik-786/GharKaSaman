
package com.example.gharkasaman.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BakeryDining
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.Egg
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.gharkasaman.ui.theme.*

data class GroceryCategory(
    val name: String,
    val icon: ImageVector
)

@Composable
fun HomeScreen() {
    var searchText by remember { mutableStateOf("") }

    val categories = listOf(
        GroceryCategory("Dairy & Eggs", Icons.Default.Egg),
        GroceryCategory("Vegetables", Icons.Default.Eco),
        GroceryCategory("Fruits", Icons.Default.LocalFlorist),
        GroceryCategory("Staples", Icons.Default.ShoppingBasket),
        GroceryCategory("Bakery", Icons.Default.BakeryDining),
        GroceryCategory("Household", Icons.Default.CleaningServices)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CreamBackground)
            .padding(horizontal = 22.dp)
            .padding(top = 60.dp)
    ) {
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

        Spacer(modifier = Modifier.height(24.dp))

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
                unfocusedBorderColor = androidx.compose.ui.graphics.Color(0xFFE5E1D9),
                focusedContainerColor = AppWhite,
                unfocusedContainerColor = AppWhite
            )
        )

        Spacer(modifier = Modifier.height(28.dp))

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

        Spacer(modifier = Modifier.height(18.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(bottom = 20.dp)
        ) {
            items(categories) { category ->
                CategoryCard(category = category)
            }
        }
    }
}

@Composable
fun CategoryCard(category: GroceryCategory) {
    Card(
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
