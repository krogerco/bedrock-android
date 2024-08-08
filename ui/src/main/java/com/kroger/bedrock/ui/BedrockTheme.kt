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
package com.kroger.bedrock.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration
import com.kroger.bedrock.ui.theme.BedrockDimensions
import com.kroger.bedrock.ui.theme.darkColors
import com.kroger.bedrock.ui.theme.largeDimensions
import com.kroger.bedrock.ui.theme.lightColors
import com.kroger.bedrock.ui.theme.smallDimensions

/**
 * The Composable Bedrock Theme wrapper
 */
@Composable
public fun BedrockTheme(
    content: @Composable () -> Unit,
) {
    ProvideAppDimens(dimensions = BedrockTheme.dimensions) {
        MaterialTheme(
            colors = BedrockTheme.colors,
            content = content,
            typography = BedrockTheme.typography,
        )
    }
}

/**
 * Provides access to the current [Colors] and [Typography] for the Bedrock theme.
 */
public object BedrockTheme {
    /**
     * Retrieves the current [Colors] at the call site's position in the hierarchy.
     *
     * @sample androidx.compose.material.samples.ThemeColorSample
     */
    public val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = if (isSystemInDarkTheme()) {
            darkColors
        } else {
            lightColors
        }

    public val dimensions: BedrockDimensions
        @Composable
        @ReadOnlyComposable
        get() = if (LocalConfiguration.current.screenWidthDp <= 600) {
            smallDimensions
        } else {
            largeDimensions
        }

    public val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

@Composable
public fun ProvideAppDimens(
    dimensions: BedrockDimensions,
    content: @Composable () -> Unit,
) {
    val dimensionSet = remember { dimensions }
    CompositionLocalProvider(LocalAppDimens provides dimensionSet, content = content)
}

private val LocalAppDimens = staticCompositionLocalOf {
    smallDimensions
}

private val LocalTypography = staticCompositionLocalOf { Typography() }
