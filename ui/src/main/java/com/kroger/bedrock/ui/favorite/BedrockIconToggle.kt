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
package com.kroger.bedrock.ui.favorite

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.kroger.bedrock.ui.BedrockTheme

@Composable
public fun BedrockIconToggle(
    modifier: Modifier = Modifier,
    isChecked: Boolean = false,
    enabled: Boolean = true,
    checkedIcon: ImageVector,
    uncheckedIcon: ImageVector,
    contentDescription: String,
    onPressed: (isChecked: Boolean) -> Unit,
) {
    val toggleIcon = if (isChecked) {
        checkedIcon
    } else {
        uncheckedIcon
    }

    IconToggleButton(
        checked = isChecked,
        onCheckedChange = {
            onPressed(it)
        },
        modifier = modifier,
        enabled = enabled,
    ) {
        Surface(
            modifier = Modifier
                .size(BedrockTheme.dimensions.buttonHeight)
                .alpha(LocalContentAlpha.current),
            color = BedrockTheme.colors.surface,
            shape = CircleShape,
            border = BorderStroke(
                width = BedrockTheme.dimensions.stroke,
                color = BedrockTheme.colors.primary,
            ),
        ) {
            Icon(
                imageVector = toggleIcon,
                contentDescription = contentDescription,
                modifier = Modifier.padding(BedrockTheme.dimensions.paddingSmall),
                tint = BedrockTheme.colors.primary,
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FavoriteButtonPreview() {
    BedrockTheme {
        BedrockIconToggle(
            checkedIcon = Icons.Default.Favorite,
            uncheckedIcon = Icons.Default.FavoriteBorder,
            onPressed = {},
            contentDescription = "",
        )
    }
}
