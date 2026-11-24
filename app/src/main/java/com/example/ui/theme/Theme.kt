package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

private val LightPortalColorScheme = lightColorScheme(
    primary = EmeraldPrimary,
    onPrimary = EmeraldOnPrimary,
    primaryContainer = EmeraldContainer,
    onPrimaryContainer = EmeraldOnContainer,
    secondary = AmberSecondary,
    onSecondary = AmberOnSecondary,
    secondaryContainer = AmberContainer,
    onSecondaryContainer = AmberOnContainer,
    tertiary = CobaltTertiary,
    onTertiary = CobaltOnTertiary,
    tertiaryContainer = CobaltContainer,
    onTertiaryContainer = CobaltOnContainer,
    error = TerracottaError,
    onError = TerracottaOnError,
    errorContainer = TerracottaErrorContainer,
    onErrorContainer = TerracottaOnErrorContainer,
    background = ParchmentBackground,
    onBackground = ParchmentOnBackground,
    surface = ParchmentSurface,
    onSurface = ParchmentOnBackground,
    surfaceVariant = ParchmentSurfaceContainer,
    onSurfaceVariant = ParchmentOnSurfaceVariant,
    surfaceContainerLowest = ParchmentSurfaceLowest,
    surfaceContainerLow = ParchmentSurfaceLow,
    surfaceContainer = ParchmentSurfaceContainer,
    surfaceContainerHigh = ParchmentSurfaceHigh,
    outline = ParchmentOutline,
    outlineVariant = ParchmentOutlineVariant
)

private val DarkPortalColorScheme = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = DarkOnPrimary,
    primaryContainer = DarkPrimaryContainer,
    onPrimaryContainer = DarkOnPrimaryContainer,
    secondary = DarkSecondary,
    onSecondary = DarkOnSecondary,
    secondaryContainer = DarkSecondaryContainer,
    onSecondaryContainer = DarkOnSecondaryContainer,
    tertiary = DarkTertiary,
    onTertiary = DarkOnTertiary,
    tertiaryContainer = DarkTertiaryContainer,
    onTertiaryContainer = DarkOnTertiaryContainer,
    error = TerracottaError,
    onError = TerracottaOnError,
    errorContainer = TerracottaErrorContainer,
    onErrorContainer = TerracottaOnErrorContainer,
    background = DarkBackground,
    onBackground = DarkOnBackground,
    surface = DarkSurface,
    onSurface = DarkOnBackground,
    surfaceVariant = DarkSurfaceContainer,
    onSurfaceVariant = DarkOnSurfaceVariant,
    surfaceContainerLowest = DarkSurfaceLowest,
    surfaceContainerLow = DarkSurfaceLow,
    surfaceContainer = DarkSurfaceContainer,
    surfaceContainerHigh = DarkSurfaceHigh,
    outline = DarkOutline,
    outlineVariant = DarkOutlineVariant
)

val PortalShapes = Shapes(
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(22.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

@Composable
fun Class9PortalTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkPortalColorScheme else LightPortalColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = PortalTypography,
        shapes = PortalShapes,
        content = content
    )
}
