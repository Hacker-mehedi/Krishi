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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CommunityPost
import com.example.ui.theme.KrishiGreenPrimary

@Composable
fun AddPostDialog(
    onDismiss: () -> Unit,
    onPostCreated: (CommunityPost) -> Unit
) {
    var contentText by remember { mutableStateOf("") }
    var authorName by remember { mutableStateOf("মোঃ আবদুল করিম") }
    var locationDistrict by remember { mutableStateOf("দিনাজপুর সদর") }
    var selectedTag by remember { mutableStateOf("পরামর্শ চাই") }
    var hasPhoto by remember { mutableStateOf(false) }

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
                        imageVector = Icons.Default.PostAdd,
                        contentDescription = "নতুন পোস্ট",
                        tint = KrishiGreenPrimary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "কৃষক আড্ডায় লিখুন",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
                IconButton(onClick = onDismiss) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                }
            }
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Post tag pills
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    listOf("পরামর্শ চাই", "ফলনের খবর", "অভিজ্ঞতা শেয়ার").forEach { tag ->
                        val isSelected = selectedTag == tag
                        Surface(
                            onClick = { selectedTag = tag },
                            shape = RoundedCornerShape(16.dp),
                            color = if (isSelected) KrishiGreenPrimary else MaterialTheme.colorScheme.surfaceVariant
                        ) {
                            Text(
                                text = tag,
                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = contentText,
                    onValueChange = { contentText = it },
                    label = { Text("আপনার ফসলের কথা বা প্রশ্ন লিখুন...") },
                    placeholder = { Text("যেমন: আমার আমন ধানের পাতায় বাদামি দাগ হয়েছে, কী ওষুধ দিব?") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .testTag("post_content_input"),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Location indication
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "লোকেশন",
                        tint = KrishiGreenPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "আপনার এলাকা: $locationDistrict",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Photo attach simulation
                Surface(
                    onClick = { hasPhoto = !hasPhoto },
                    shape = RoundedCornerShape(10.dp),
                    color = if (hasPhoto) Color(0xFFE8F5E9) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = if (hasPhoto) Icons.Default.Check else Icons.Default.AddPhotoAlternate,
                            contentDescription = "ছবি যুক্ত করুন",
                            tint = if (hasPhoto) KrishiGreenPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (hasPhoto) "ফসলের ছবি যুক্ত হয়েছে ✓" else "ফসলের ছবি বা পাতা যুক্ত করুন",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = if (hasPhoto) KrishiGreenPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (contentText.isNotBlank()) {
                        val newPost = CommunityPost(
                            id = "post_${System.currentTimeMillis()}",
                            authorNameBn = authorName,
                            authorRoleBn = "কৃষক",
                            authorDistrictBn = locationDistrict,
                            timeAgoBn = "এইমাত্র",
                            textContentBn = "[$selectedTag] $contentText",
                            imageUrl = if (hasPhoto) "https://images.unsplash.com/photo-1598170845058-32b9d6a5da37?w=800&auto=format&fit=crop" else null,
                            likesCount = 0,
                            commentsCount = 0,
                            sharesCount = 0,
                            isLikedByUser = false,
                            comments = mutableListOf()
                        )
                        onPostCreated(newPost)
                        onDismiss()
                    }
                },
                enabled = contentText.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = KrishiGreenPrimary),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.testTag("submit_post_btn")
            ) {
                Text("পোস্ট করুন", fontWeight = FontWeight.Bold)
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
