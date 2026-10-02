package com.example.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.MarketProduct
import com.example.data.model.ProductCategory
import com.example.ui.theme.KrishiGreenPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductDialog(
    onDismiss: () -> Unit,
    onProductAdded: (MarketProduct) -> Unit
) {
    var productName by remember { mutableStateOf("") }
    var priceText by remember { mutableStateOf("") }
    var unitName by remember { mutableStateOf("কেজি") }
    var stockText by remember { mutableStateOf("১০০") }
    var sellerPhone by remember { mutableStateOf("01712345678") }
    var district by remember { mutableStateOf("দিনাজপুর") }
    var description by remember { mutableStateOf("") }
    var isOrganic by remember { mutableStateOf(true) }

    var selectedCategory by remember { mutableStateOf(ProductCategory.CROPS) }
    var categoryExpanded by remember { mutableStateOf(false) }

    val categories = listOf(
        ProductCategory.CROPS,
        ProductCategory.VEGETABLES,
        ProductCategory.FRUITS,
        ProductCategory.SEEDS,
        ProductCategory.FERTILIZER,
        ProductCategory.SPICES
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AddShoppingCart,
                        contentDescription = "পণ্য বিক্রি",
                        tint = KrishiGreenPrimary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "ফসল বা পণ্য বিক্রির বিজ্ঞাপন",
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                }
                IconButton(onClick = onDismiss) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                }
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                OutlinedTextField(
                    value = productName,
                    onValueChange = { productName = it },
                    label = { Text("পণ্যের নাম (যেমন: তাজা মিনিকেট চাল)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("product_name_input"),
                    shape = RoundedCornerShape(10.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Category dropdown
                ExposedDropdownMenuBox(
                    expanded = categoryExpanded,
                    onExpandedChange = { categoryExpanded = !categoryExpanded }
                ) {
                    OutlinedTextField(
                        value = selectedCategory.titleBn,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("ক্যাটাগরি") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    )
                    ExposedDropdownMenu(
                        expanded = categoryExpanded,
                        onDismissRequest = { categoryExpanded = false }
                    ) {
                        categories.forEach { cat ->
                            DropdownMenuItem(
                                text = { Text(cat.titleBn) },
                                onClick = {
                                    selectedCategory = cat
                                    categoryExpanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = priceText,
                        onValueChange = { priceText = it },
                        label = { Text("দাম (টাকা)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .weight(1.2f)
                            .testTag("product_price_input"),
                        shape = RoundedCornerShape(10.dp)
                    )

                    OutlinedTextField(
                        value = unitName,
                        onValueChange = { unitName = it },
                        label = { Text("একক (কেজি/মণ)") },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = stockText,
                        onValueChange = { stockText = it },
                        label = { Text("মজুদ পরিমাণ") },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp)
                    )
                    OutlinedTextField(
                        value = district,
                        onValueChange = { district = it },
                        label = { Text("আপনার জেলা") },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = sellerPhone,
                    onValueChange = { sellerPhone = it },
                    label = { Text("যোগাযোগের মোবাইল নম্বর") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("পণ্য ও গুণগত মান সম্পর্কে বিবরণ") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    maxLines = 3
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isOrganic,
                        onCheckedChange = { isOrganic = it },
                        colors = CheckboxDefaults.colors(checkedColor = KrishiGreenPrimary)
                    )
                    Text(
                        text = "এটি বিষমুক্ত ও রাসায়নিকহীন জৈব ফসল",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val price = priceText.toDoubleOrNull() ?: 100.0
                    val newProd = MarketProduct(
                        id = "prod_${System.currentTimeMillis()}",
                        nameBn = productName.ifEmpty { "তাজা কৃষিপণ্য" },
                        category = selectedCategory,
                        pricePerUnit = price,
                        unitBn = unitName,
                        availableStockBn = "$stockText $unitName",
                        sellerNameBn = "মোঃ আবদুল করিম",
                        sellerPhone = sellerPhone,
                        sellerDistrictBn = district,
                        sellerUpazilaBn = "সদর",
                        isVerifiedFarmer = true,
                        isOrganicCertified = isOrganic,
                        descriptionBn = description.ifEmpty { "কৃষকের নিজস্ব খামার থেকে সরাসরি সংগৃহীত তাজা পণ্য।" },
                        rating = 5.0,
                        imageUrl = when (selectedCategory) {
                            ProductCategory.CROPS -> "https://images.unsplash.com/photo-1586201375761-83865001e31c?w=800&auto=format&fit=crop"
                            ProductCategory.VEGETABLES -> "https://images.unsplash.com/photo-1598170845058-32b9d6a5da37?w=800&auto=format&fit=crop"
                            ProductCategory.FRUITS -> "https://images.unsplash.com/photo-1553279768-865429fa0078?w=800&auto=format&fit=crop"
                            ProductCategory.SEEDS -> "https://images.unsplash.com/photo-1508873696983-2df5293cb32f?w=800&auto=format&fit=crop"
                            ProductCategory.FERTILIZER -> "https://images.unsplash.com/photo-1615811361523-6bd03d7748e7?w=800&auto=format&fit=crop"
                            else -> "https://images.unsplash.com/photo-1518977676601-b53f82aba655?w=800&auto=format&fit=crop"
                        }
                    )
                    onProductAdded(newProd)
                    onDismiss()
                },
                enabled = productName.isNotBlank() && priceText.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = KrishiGreenPrimary),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.testTag("submit_product_btn")
            ) {
                Text("বিজ্ঞাপন দিন", fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            OutlinedButton(
                onClick = onDismiss,
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("বাতিল")
            }
        }
    )
}
