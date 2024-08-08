/**
 * MIT License
 *
 * Copyright (c) 2024 The Kroger Co. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.kroger.bedrock.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

// TODO: Migrate to Material3 when stable
// Leaving material 3 colors as unused until that time
private val md_theme_light_primary: Color = Color(0xFF295DAC)
private val md_theme_light_onPrimary: Color = Color(0xFFFFFFFF)
private val md_theme_light_primaryContainer: Color = Color(0xFFD7E2FF)
private val md_theme_light_onPrimaryContainer: Color = Color(0xFF001A40)
private val md_theme_light_secondary: Color = Color(0xFF00639C)
private val md_theme_light_onSecondary: Color = Color(0xFFFFFFFF)
private val md_theme_light_secondaryContainer: Color = Color(0xFFCFE5FF)
private val md_theme_light_onSecondaryContainer: Color = Color(0xFF001D33)
private val md_theme_light_tertiary: Color = Color(0xFF006684)
private val md_theme_light_onTertiary: Color = Color(0xFFFFFFFF)
private val md_theme_light_tertiaryContainer: Color = Color(0xFFBDE9FF)
private val md_theme_light_onTertiaryContainer: Color = Color(0xFF001F2A)
private val md_theme_light_error: Color = Color(0xFFBA1A1A)
private val md_theme_light_errorContainer: Color = Color(0xFFFFDAD6)
private val md_theme_light_onError: Color = Color(0xFFFFFFFF)
private val md_theme_light_onErrorContainer: Color = Color(0xFF410002)
private val md_theme_light_background: Color = Color(0xFFFFFBFF)
private val md_theme_light_onBackground: Color = Color(0xFF000000)
private val md_theme_light_surface: Color = Color(0xFFFFFBFF)
private val md_theme_light_onSurface: Color = Color(0xFF000000)
private val md_theme_light_surfaceVariant: Color = Color(0xFFE0E2EC)
private val md_theme_light_onSurfaceVariant: Color = Color(0xFF44474E)
private val md_theme_light_outline: Color = Color(0xFF74777F)
private val md_theme_light_inverseOnSurface: Color = Color(0xFFF6EDFF)
private val md_theme_light_inverseSurface: Color = Color(0xFF3A1D71)
private val md_theme_light_inversePrimary: Color = Color(0xFFACC7FF)
private val md_theme_light_shadow: Color = Color(0xFF000000)
private val md_theme_light_surfaceTint: Color = Color(0xFF295DAC)
private val md_theme_light_outlineVariant: Color = Color(0xFFC4C6D0)
private val md_theme_light_scrim: Color = Color(0xFF000000)

private val md_theme_dark_primary: Color = Color(0xFFACC7FF)
private val md_theme_dark_onPrimary: Color = Color(0xFF002F67)
private val md_theme_dark_primaryContainer: Color = Color(0xFF004591)
private val md_theme_dark_onPrimaryContainer: Color = Color(0xFFD7E2FF)
private val md_theme_dark_secondary: Color = Color(0xFF98CBFF)
private val md_theme_dark_onSecondary: Color = Color(0xFF003354)
private val md_theme_dark_secondaryContainer: Color = Color(0xFF004A77)
private val md_theme_dark_onSecondaryContainer: Color = Color(0xFFCFE5FF)
private val md_theme_dark_tertiary: Color = Color(0xFF66D3FF)
private val md_theme_dark_onTertiary: Color = Color(0xFF003546)
private val md_theme_dark_tertiaryContainer: Color = Color(0xFF004D64)
private val md_theme_dark_onTertiaryContainer: Color = Color(0xFFBDE9FF)
private val md_theme_dark_error: Color = Color(0xFFFFB4AB)
private val md_theme_dark_errorContainer: Color = Color(0xFF93000A)
private val md_theme_dark_onError: Color = Color(0xFF690005)
private val md_theme_dark_onErrorContainer: Color = Color(0xFFFFDAD6)
private val md_theme_dark_background: Color = Color(0xFF000000)
private val md_theme_dark_onBackground: Color = Color(0xFFEADDFF)
private val md_theme_dark_surface: Color = Color(0xFF000000)
private val md_theme_dark_onSurface: Color = Color(0xFFEADDFF)
private val md_theme_dark_surfaceVariant: Color = Color(0xFF44474E)
private val md_theme_dark_onSurfaceVariant: Color = Color(0xFFC4C6D0)
private val md_theme_dark_outline: Color = Color(0xFF8E9099)
private val md_theme_dark_inverseOnSurface: Color = Color(0xFF000000)
private val md_theme_dark_inverseSurface: Color = Color(0xFFEADDFF)
private val md_theme_dark_inversePrimary: Color = Color(0xFF295DAC)
private val md_theme_dark_shadow: Color = Color(0xFF000000)
private val md_theme_dark_surfaceTint: Color = Color(0xFFACC7FF)
private val md_theme_dark_outlineVariant: Color = Color(0xFF44474E)
private val md_theme_dark_scrim: Color = Color(0xFF000000)

private val seed: Color = Color(0xFF0C4B99)

public val lightColors: Colors = lightColors(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    error = md_theme_light_error,
    onError = md_theme_light_onError,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
)

public val darkColors: Colors = darkColors(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    error = md_theme_dark_error,
    onError = md_theme_dark_onError,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
)
