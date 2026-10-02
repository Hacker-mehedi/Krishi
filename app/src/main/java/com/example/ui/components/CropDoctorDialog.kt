package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.Healing
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.KrishiGoldHarvest
import com.example.ui.theme.KrishiGreenPrimary
import kotlinx.coroutines.delay

data class DiseaseDiagnosis(
    val diseaseNameBn: String,
    val probabilityPercent: Int,
    val causeBn: String,
    val symptomsBn: String,
    val chemicalRemedyBn: String,
    val organicRemedyBn: String,
    val preventiveCareBn: String
)

@Composable
fun CropDoctorDialog(
    onDismiss: () -> Unit
) {
    var selectedCrop by remember { mutableStateOf("ধান") }
    var selectedSymptom by remember { mutableStateOf("পাতায় বাদামি দাগ ও শুকিয়ে যাওয়া") }
    var isAnalyzing by remember { mutableStateOf(false) }
    var diagnosisResult by remember { mutableStateOf<DiseaseDiagnosis?>(null) }

    val crops = listOf("ধান", "গোল আলু", "বেগুন", "সরিষা", "টমেটো")
    val symptoms = listOf(
        "পাতায় বাদামি দাগ ও শুকিয়ে যাওয়া",
        "পাতা হলুদ হয়ে কুঁকড়ে যাওয়া",
        "গাছের কচি ডগা ও কাণ্ড পচে ঢলে পড়া",
        "পাতার কিনারে পানিভেজা পোড়া দাগ"
    )

    fun runDiagnosis() {
        isAnalyzing = true
        diagnosisResult = null
    }

    LaunchedEffect(isAnalyzing) {
        if (isAnalyzing) {
            delay(1200) // Simulating rapid AI diagnosis
            isAnalyzing = false
            diagnosisResult = when (selectedCrop) {
                "গোল আলু" -> DiseaseDiagnosis(
                    diseaseNameBn = "আলুর নাবিধসা রোগ (Late Blight)",
                    probabilityPercent = 94,
                    causeBn = "ফাইটোফথোরা ইনফেস্ট্যান্স নামক ছত্রাক (অতিরিক্ত কুয়াশা ও স্যাঁতসেঁতে আবহাওয়ায় বিস্তার লাভ করে)",
                    symptomsBn = "পাতার ডগায় কালচে পানিভেজা দাগ, দ্রুত পুরো পাতা ও ডালে ছড়িয়ে পড়ে, পাতার নিচে সাদা পাউডারের মতো ছত্রাক।",
                    chemicalRemedyBn = "ম্যানকোজেব (ডায়থেন এম-৪৫) অথবা রিডোমিল গোল্ড প্রতি লিটার পানিতে ২ গ্রাম হারে স্প্রে করুন।",
                    organicRemedyBn = "ট্রাইকোডার্মা জৈব ছত্রাকনাশক ব্যবহার করুন এবং আক্রান্ত গাছ তুলে ধ্বংস করুন।",
                    preventiveCareBn = "কুয়াশার পূর্বাভাস থাকলে রোগ আসার আগেই আগাম স্প্রে করুন। জমিতে অতিরিক্ত পানি জমতে দেবেন না।"
                )
                "বেগুন" -> DiseaseDiagnosis(
                    diseaseNameBn = "বেগুনের ডগা ও ফল ছিদ্রকারী পোকা (Leucinodes orbonalis)",
                    probabilityPercent = 91,
                    causeBn = "এক ধরনের ক্ষতিকর মথের কীড়া যা ডগার ভেতরে ঢুকে মজ্জা খায়",
                    symptomsBn = "গাছের কচি ডগা নুয়ে পড়ে এবং শুকিয়ে যায়। বেগুনের গায়ে ছোট ছোট ছিদ্র ও মল দেখা যায়।",
                    chemicalRemedyBn = "স্পাইনোসেড (ট্রেসার) প্রতি লিটার পানিতে ০.৪ মিলি অথবা এমামেকটিন বেনজোয়েট ১ গ্রাম মিশিয়ে স্প্রে করুন।",
                    organicRemedyBn = "বিঘায় ৪-৫টি সেক্স ফেরোমোন ফাঁদ স্থাপন করুন এবং আক্রান্ত ডগা হাত দিয়ে ভেঙে পুড়িয়ে ফেলুন।",
                    preventiveCareBn = "একই জমিতে বারবার বেগুন চাষ না করে শস্য পর্যায়ক্রম অনুসরণ করুন।"
                )
                else -> DiseaseDiagnosis(
                    diseaseNameBn = "ধানের ব্লাস্ট ও পাতা পোড়া রোগ (Rice Blast)",
                    probabilityPercent = 96,
                    causeBn = "ম্যাগনাপোর্থি ওরাইজি নামক মারাত্মক ছত্রাক",
                    symptomsBn = "পাতায় চোখের মতো দুই প্রান্তে চোখা বাদামি দাগ, শিষের গোড়া কালো হয়ে শিষ ভেঙে পড়া ও চিটা হওয়া।",
                    chemicalRemedyBn = "ট্রুপার (ট্রাইসাইক্লাজল) অথবা নেটিভো ০.৬ গ্রাম প্রতি লিটার পানিতে মিশিয়ে বিকেলে স্প্রে করুন।",
                    organicRemedyBn = "জমিতে অতিরিক্ত ইউরিয়া সার প্রয়োগ বন্ধ রাখুন এবং কাঠ কয়লার ছাই ছিটিয়ে দিন।",
                    preventiveCareBn = "জমিতে সর্বদা পর্যাপ্ত পানি ধরে রাখুন এবং সুষম পটাশ সার ব্যবহার করুন।"
                )
            }
        }
    }

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
                        imageVector = Icons.Default.MedicalServices,
                        contentDescription = "ডাক্তার",
                        tint = KrishiGreenPrimary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "স্মার্ট ফসল ডাক্তার (AI Doctor)",
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
                    .testTag("crop_doctor_dialog")
            ) {
                Text(
                    text = "ফসল নির্বাচন করুন:",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    crops.forEach { crop ->
                        val isSelected = selectedCrop == crop
                        Surface(
                            onClick = {
                                selectedCrop = crop
                                diagnosisResult = null
                            },
                            shape = RoundedCornerShape(12.dp),
                            color = if (isSelected) KrishiGreenPrimary else MaterialTheme.colorScheme.surfaceVariant
                        ) {
                            Text(
                                text = crop,
                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "গাছের লক্ষণ বা পাতার সমস্যা:",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                symptoms.forEach { symptom ->
                    val isSelected = selectedSymptom == symptom
                    Surface(
                        onClick = {
                            selectedSymptom = symptom
                            diagnosisResult = null
                        },
                        shape = RoundedCornerShape(10.dp),
                        color = if (isSelected) Color(0xFFE8F5E9) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = if (isSelected) Icons.Default.Check else Icons.Default.Warning,
                                contentDescription = "Symptom",
                                tint = if (isSelected) KrishiGreenPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = symptom,
                                fontSize = 12.sp,
                                color = if (isSelected) KrishiGreenPrimary else MaterialTheme.colorScheme.onSurface,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { runDiagnosis() },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = KrishiGoldHarvest)
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = "Diagnose",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("রোগ নির্ণয় ও প্রতিকার খুঁজুন", fontWeight = FontWeight.Bold, color = Color.White)
                }

                if (isAnalyzing) {
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircularProgressIndicator(
                            color = KrishiGreenPrimary,
                            strokeWidth = 3.dp,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "কৃষি বিশেষজ্ঞ এআই বিশ্লেষণ করছে...",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                // Result display
                diagnosisResult?.let { res ->
                    Spacer(modifier = Modifier.height(14.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9))
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = res.diseaseNameBn,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = Color(0xFF1B5E20)
                                )
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = KrishiGreenPrimary
                                ) {
                                    Text(
                                        text = "${res.probabilityPercent}% নিশ্চিত",
                                        color = Color.White,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "কারণ: ${res.causeBn}",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            Spacer(modifier = Modifier.height(8.dp))
                            HorizontalDivider(color = Color(0xFFDCEDC8))
                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "রাসায়নিক প্রতিকার:",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = Color(0xFFB71C1C)
                            )
                            Text(
                                text = res.chemicalRemedyBn,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                lineHeight = 16.sp
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "জৈব প্রতিকার:",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = Color(0xFF2E7D32)
                            )
                            Text(
                                text = res.organicRemedyBn,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                lineHeight = 16.sp
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "সতর্কতা: ${res.preventiveCareBn}",
                                fontSize = 11.sp,
                                color = Color(0xFF558B2F),
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = KrishiGreenPrimary)
            ) {
                Text("বন্ধ করুন")
            }
        }
    )
}
