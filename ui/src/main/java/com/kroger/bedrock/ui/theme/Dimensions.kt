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

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

public data class BedrockDimensions(
    val stroke: Dp = 1.dp,
    val paddingSmall: Dp = 4.dp,
    val padding: Dp = 8.dp,
    val paddingLarge: Dp = 16.dp,
    val paddingVeryLarge: Dp = 32.dp,
    val gutter: Dp = 16.dp,
    val buttonHeight: Dp = 48.dp,
    val elevation: Dp = 8.dp,
)

public val smallDimensions: BedrockDimensions = BedrockDimensions(
    buttonHeight = 48.dp,
)

public val largeDimensions: BedrockDimensions = BedrockDimensions(
    buttonHeight = 128.dp,
)
