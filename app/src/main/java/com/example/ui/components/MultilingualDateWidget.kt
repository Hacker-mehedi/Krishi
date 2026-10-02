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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.EventNote
import androidx.compose.material.icons.filled.Grass
import androidx.compose.material.icons.filled.Mosque
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.example.utils.BanglaDateHelper

@Composable
fun MultilingualDateWidget(
    modifier: Modifier = Modifier
) {
    val dateInfo = remember { BanglaDateHelper.getCurrentMultilingualDate() }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("multilingual_date_card"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            // Header: Section label & Season pill
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.CalendarMonth,
                                contentDescription = "পঞ্জিকা",
                                tint = KrishiGreenPrimary,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "বহুভাষিক কৃষি পঞ্জিকা",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                // Season Badge
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(dateInfo.currentSeason.tagColorHex).copy(alpha = 0.15f)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Grass,
                            contentDescription = "মৌসুম",
                            tint = Color(dateInfo.currentSeason.tagColorHex),
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${dateInfo.currentSeason.titleBn} (${dateInfo.currentSeason.monthsBn})",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(dateInfo.currentSeason.tagColorHex)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 3 Multilingual Date strips:
            // 1. Bangla Bongabdo
            DateItemRow(
                badgeLabel = "বাংলা",
                badgeColor = KrishiGreenPrimary,
                dateText = dateInfo.banglaDateFormatted,
                subText = "বঙ্গাব্দ (বাংলা একাডেমি অনুমোদিত)"
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 2. English Gregorian
            DateItemRow(
                badgeLabel = "English",
                badgeColor = Color(0xFF1565C0),
                dateText = dateInfo.englishDateFormatted,
                subText = "Gregorian Calendar"
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 3. Islamic Hijri
            DateItemRow(
                badgeLabel = "হিজরি",
                badgeColor = KrishiGoldHarvest,
                dateText = dateInfo.hijriDateFormatted,
                subText = "ইসলামিক আরবি বর্ষপঞ্জি"
            )
        }
    }
}

@Composable
private fun DateItemRow(
    badgeLabel: String,
    badgeColor: Color,
    dateText: String,
    subText: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
            .padding(horizontal = 10.dp, vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            shape = RoundedCornerShape(6.dp),
            color = badgeColor,
            modifier = Modifier.width(52.dp)
        ) {
            Text(
                text = badgeLabel,
                color = Color.White,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = Modifier.padding(vertical = 3.dp)
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = dateText,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = subText,
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
