package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = KrishiGreenPrimaryDark,
    onPrimary = Color(0xFF003A08),
    primaryContainer = KrishiGreenContainerDark,
    onPrimaryContainer = Color(0xFFA5F4A9),
    secondary = KrishiGoldLight,
    onSecondary = Color(0xFF4A3000),
    secondaryContainer = Color(0xFF5E3F00),
    onSecondaryContainer = Color(0xFFFFDEA3),
    tertiary = Color(0xFF81D4FA),
    background = KrishiBackgroundDark,
    surface = KrishiSurfaceDark,
    surfaceVariant = KrishiSurfaceVariantDark,
    onBackground = KrishiTextPrimaryDark,
    onSurface = KrishiTextPrimaryDark,
    onSurfaceVariant = KrishiTextSecondaryDark
)

private val LightColorScheme = lightColorScheme(
    primary = KrishiGreenPrimary,
    onPrimary = Color.White,
    primaryContainer = KrishiGreenContainer,
    onPrimaryContainer = KrishiOnGreenContainer,
    secondary = KrishiGoldHarvest,
    onSecondary = Color.White,
    secondaryContainer = KrishiGoldContainer,
    onSecondaryContainer = KrishiOnGoldContainer,
    tertiary = KrishiSkyBlue,
    background = KrishiBackgroundLight,
    surface = KrishiSurfaceLight,
    surfaceVariant = KrishiSurfaceVariantLight,
    onBackground = KrishiTextPrimary,
    onSurface = KrishiTextPrimary,
    onSurfaceVariant = KrishiTextSecondary
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Keep branded agricultural aesthetic by default
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
