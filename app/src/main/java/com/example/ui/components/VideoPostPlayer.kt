package com.example.ui.components

import android.net.Uri
import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.VolumeMute
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.viewinterop.AndroidView
import com.example.ui.theme.KrishiGreenPrimary
import com.example.utils.BanglaDateHelper
import kotlinx.coroutines.delay

@Composable
fun VideoPostPlayer(
    videoUrl: String,
    title: String? = null,
    modifier: Modifier = Modifier
) {
    var isPlaying by remember { mutableStateOf(false) }
    var isMuted by remember { mutableStateOf(false) }
    var currentProgress by remember { mutableFloatStateOf(0f) }
    var currentSeconds by remember { mutableIntStateOf(0) }
    var durationSeconds by remember { mutableIntStateOf(180) }
    var hasStarted by remember { mutableStateOf(false) }

    // Simulation/Player progress loop
    LaunchedEffect(isPlaying) {
        while (isPlaying) {
            delay(1000)
            if (currentSeconds < durationSeconds) {
                currentSeconds += 1
                currentProgress = currentSeconds.toFloat() / durationSeconds
            } else {
                isPlaying = false
                currentSeconds = 0
                currentProgress = 0f
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(210.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF0F1711))
            .testTag("video_player_card")
    ) {
        if (hasStarted) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { ctx ->
                    VideoView(ctx).apply {
                        try {
                            setVideoURI(Uri.parse(videoUrl))
                            setOnPreparedListener { mp ->
                                durationSeconds = (mp.duration / 1000).coerceAtLeast(30)
                                mp.isLooping = true
                                if (isMuted) mp.setVolume(0f, 0f) else mp.setVolume(1f, 1f)
                                start()
                            }
                        } catch (e: Exception) {
                            // Handled gracefully
                        }
                    }
                },
                update = { videoView ->
                    if (isPlaying && !videoView.isPlaying) {
                        videoView.start()
                    } else if (!isPlaying && videoView.isPlaying) {
                        videoView.pause()
                    }
                }
            )
        }

        // Overlay with gradient and controls
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    if (!hasStarted) Color.Black.copy(alpha = 0.65f)
                    else Color.Black.copy(alpha = 0.25f)
                )
        )

        // Center Big Play/Pause Button
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(56.dp)
                .background(KrishiGreenPrimary.copy(alpha = 0.85f), CircleShape)
                .clickable {
                    hasStarted = true
                    isPlaying = !isPlaying
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = if (isPlaying) "থামান" else "চালান",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }

        // Top Video Title Banner
        if (title != null) {
            Surface(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(10.dp),
                shape = RoundedCornerShape(8.dp),
                color = Color.Black.copy(alpha = 0.6f)
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }

        // Bottom Controls Bar
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.7f))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            LinearProgressIndicator(
                progress = { currentProgress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(3.dp),
                color = KrishiGreenPrimary,
                trackColor = Color.White.copy(alpha = 0.3f),
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val currentMin = currentSeconds / 60
                val currentSec = currentSeconds % 60
                val totalMin = durationSeconds / 60
                val totalSec = durationSeconds % 60

                Text(
                    text = "${BanglaDateHelper.toBanglaDigits(String.format("%02d:%02d", currentMin, currentSec))} / ${BanglaDateHelper.toBanglaDigits(String.format("%02d:%02d", totalMin, totalSec))}",
                    color = Color.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = { isMuted = !isMuted },
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = if (isMuted) Icons.Default.VolumeMute else Icons.Default.VolumeUp,
                            contentDescription = if (isMuted) "শব্দ চালু" else "শব্দ বন্ধ",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    IconButton(
                        onClick = {
                            currentSeconds = 0
                            currentProgress = 0f
                            isPlaying = true
                        },
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Replay,
                            contentDescription = "পুনরায় চালান",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}
