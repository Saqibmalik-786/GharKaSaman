
package com.example.gharkasaman

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val PrimaryGreen = Color(0xFF2E6B45)
val CreamBackground = Color(0xFFFFF8F0)
val SecondaryText = Color(0xFF777777)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GroceryHomeScreen()
        }
    }
}

@Composable
fun GroceryHomeScreen() {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CreamBackground)
            .padding(20.dp)

    ) {
        Text(
            text = "Ghar Ka Saman",
            fontSize = 28.sp,
            color = PrimaryGreen,
            fontWeight = FontWeight.W700
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Aaj kya khareedna hai?",
            fontSize = 16.sp,
            color = SecondaryText
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text("Saman search karein...")
            },
            shape = RoundedCornerShape(14.dp),
            singleLine = true
        )
    }
}
