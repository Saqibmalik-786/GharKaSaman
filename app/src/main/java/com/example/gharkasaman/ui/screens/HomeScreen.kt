
package com.example.gharkasaman.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.gharkasaman.ui.theme.*

@Composable
fun HomeScreen() {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CreamBackground)
            .padding(horizontal = 22.dp)
            .padding(top = 60.dp)
    ) {
        // App Title
        Text(
            text = "Ghar Ka Saman",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = DarkGreen
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Tagline
        Text(
            text = "Your daily groceries, simply organized.",
            fontSize = 15.sp,
            color = SecondaryText
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Search Bar
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
                Text("⌕", fontSize = 27.sp)
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
    }
}
