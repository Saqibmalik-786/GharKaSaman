
package com.example.gharkasaman.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gharkasaman.ui.theme.*

@Composable
fun MyListScreen(
    groceryList: Map<String, Int>,
    purchasedItems: Set<String>,
    onTogglePurchased: (String) -> Unit,
    onRemoveItem: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 22.dp)
            .padding(top = 60.dp)
    ) {
        Text(
            text = "My Grocery List",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = DarkGreen
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${groceryList.size} items in your list",
            fontSize = 14.sp,
            color = SecondaryText
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (groceryList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Your list is empty",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = DarkGreen
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Add groceries from the Home screen.",
                        color = SecondaryText,
                        fontSize = 14.sp
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                items(groceryList.keys.toList()) { name ->
                    val quantity = groceryList[name] ?: 1
                    val isPurchased = name in purchasedItems

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
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = isPurchased,
                                onCheckedChange = {
                                    onTogglePurchased(name)
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = PrimaryGreen
                                )
                            )

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = name,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = if (isPurchased) {
                                        SecondaryText
                                    } else {
                                        DarkGreen
                                    },
                                    textDecoration = if (isPurchased) {
                                        TextDecoration.LineThrough
                                    } else {
                                        TextDecoration.None
                                    }
                                )

                                Spacer(
                                    modifier = Modifier.height(4.dp)
                                )

                                Text(
                                    text = "Quantity: $quantity",
                                    fontSize = 13.sp,
                                    color = SecondaryText
                                )
                            }

                            IconButton(
                                onClick = {
                                    onRemoveItem(name)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Remove $name",
                                    tint = PrimaryGreen
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
