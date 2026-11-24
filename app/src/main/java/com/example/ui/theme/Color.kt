package com.example.ui.theme

import androidx.compose.ui.graphics.Color

// Warm Editorial Academic Palette — Scholar's Parchment & Emerald Ink
val EmeraldPrimary = Color(0xFF0A423F)
val EmeraldOnPrimary = Color(0xFFFFFFFF)
val EmeraldContainer = Color(0xFFD2ECE8)
val EmeraldOnContainer = Color(0xFF032624)

val AmberSecondary = Color(0xFF9A6510)
val AmberOnSecondary = Color(0xFFFFFFFF)
val AmberContainer = Color(0xFFFBECD0)
val AmberOnContainer = Color(0xFF331F00)

val CobaltTertiary = Color(0xFF234E8C)
val CobaltOnTertiary = Color(0xFFFFFFFF)
val CobaltContainer = Color(0xFFDCE8FA)
val CobaltOnContainer = Color(0xFF0C2447)

val TerracottaError = Color(0xFFB33B2E)
val TerracottaOnError = Color(0xFFFFFFFF)
val TerracottaErrorContainer = Color(0xFFFADCD8)
val TerracottaOnErrorContainer = Color(0xFF410E0B)

val ParchmentBackground = Color(0xFFFBF9F5)
val ParchmentOnBackground = Color(0xFF172120)
val ParchmentSurface = Color(0xFFFBF9F5)
val ParchmentSurfaceLowest = Color(0xFFFFFFFF)
val ParchmentSurfaceLow = Color(0xFFF6F2EA)
val ParchmentSurfaceContainer = Color(0xFFEFE9DD)
val ParchmentSurfaceHigh = Color(0xFFE7DFD0)
val ParchmentOutline = Color(0xFFC9C0B0)
val ParchmentOutlineVariant = Color(0xFFE2DACB)
val ParchmentOnSurfaceVariant = Color(0xFF4A5755)

// Dark Academic Slate Palette
val DarkPrimary = Color(0xFF74D7CC)
val DarkOnPrimary = Color(0xFF003734)
val DarkPrimaryContainer = Color(0xFF10524E)
val DarkOnPrimaryContainer = Color(0xFFBCEEE8)

val DarkSecondary = Color(0xFFF2C265)
val DarkOnSecondary = Color(0xFF432C00)
val DarkSecondaryContainer = Color(0xFF624200)
val DarkOnSecondaryContainer = Color(0xFFFFE0A6)

val DarkTertiary = Color(0xFF9AC0F8)
val DarkOnTertiary = Color(0xFF082E63)
val DarkTertiaryContainer = Color(0xFF1A427A)
val DarkOnTertiaryContainer = Color(0xFFDCE8FA)

val DarkBackground = Color(0xFF0E1615)
val DarkOnBackground = Color(0xFFE6ECEA)
val DarkSurface = Color(0xFF0E1615)
val DarkSurfaceLowest = Color(0xFF0A1110)
val DarkSurfaceLow = Color(0xFF15201F)
val DarkSurfaceContainer = Color(0xFF1B2928)
val DarkSurfaceHigh = Color(0xFF233331)
val DarkOutline = Color(0xFF536360)
val DarkOutlineVariant = Color(0xFF2E3E3C)
val DarkOnSurfaceVariant = Color(0xFFB3C3C0)

// Subject Accent Swatches (used consistently on subject cards & banners)
object SubjectAccents {
    val palette: Map<String, Color> = mapOf(
        "emerald" to Color(0xFF0F5E57),
        "cobalt" to Color(0xFF21559E),
        "amber" to Color(0xFFB57614),
        "terracotta" to Color(0xFFB44C3A),
        "plum" to Color(0xFF6B3F7B),
        "teal" to Color(0xFF166F7A),
        "indigo" to Color(0xFF3949AB),
        "sage" to Color(0xFF3B6E4C),
        "crimson" to Color(0xFF9C2D41)
    )

    fun colorFor(key: String): Color = palette[key] ?: EmeraldPrimary
}
