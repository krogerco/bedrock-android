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
package com.kroger.bedrock.ui.appbar

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocalAirport
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kroger.bedrock.ui.BedrockTheme
import com.kroger.bedrock.ui.R

/**
 * [BedrockAppBar] is a [TopAppBar] with a title, navigation icon, and actions.
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
public fun BedrockAppBar(
    title: @Composable () -> Unit,
    navigationIcon: @Composable (() -> Unit)? = null,
    appBarActions: @Composable RowScope.() -> Unit = {},
    content: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = title,
                backgroundColor = BedrockTheme.colors.background,
                navigationIcon = navigationIcon,
                actions = appBarActions,
            )
        },
    ) {
        content()
    }
}

/**
 * [UpButton] is intended to be used as the navigation icon in a [BedrockAppBar], but other icons should be used as needed.
 */
@Composable
public fun UpButton(onUpButtonPressed: () -> Unit) {
    IconButton(
        onClick = onUpButtonPressed,
        content = {
            Icon(
                Icons.Filled.ArrowBack,
                stringResource(R.string.content_description_up_button),
            )
        },
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BedrockAppBarPreview() {
    BedrockTheme {
        BedrockAppBar(
            title = { Text("Your Title Here") },
            navigationIcon = { UpButton { } },
            appBarActions = {
                PreviewAppBarIcon(imageVector = Icons.Default.LocalAirport)
                PreviewAppBarIcon(imageVector = Icons.Default.Android)
            },
        ) { }
    }
}

@Composable
internal fun PreviewAppBarIcon(imageVector: ImageVector, contentDescription: String = "") {
    Row {
        IconButton(
            onClick = { },
            content = {
                Icon(
                    imageVector = imageVector,
                    contentDescription = contentDescription,
                    tint = BedrockTheme.colors.primary,
                    modifier = Modifier.size(BedrockTheme.dimensions.buttonHeight),
                )
            },
        )
    }
}
