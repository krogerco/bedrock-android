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
package com.kroger.bedrock.ui.tabs

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.kroger.bedrock.ui.BedrockTheme

@Composable
public fun BedrockTabs(
    modifier: Modifier = Modifier,
    surfaceColor: Color = BedrockTheme.colors.background,
    tabItems: List<BedrockTabItem>,
) {
    var tabIndex: Int by rememberSaveable { mutableStateOf(0) }

    Surface(modifier, color = surfaceColor) {
        Column {
            TabRow(selectedTabIndex = tabIndex) {
                tabItems.forEachIndexed { index, tabItem ->
                    Tab(
                        selected = index == tabIndex,
                        onClick = { tabIndex = index },
                        text = tabItem.title,
                    )
                }
            }

            tabItems[tabIndex].content.invoke()
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BedrockTabsPreview() {
    BedrockTheme {
        BedrockTabs(
            tabItems = listOf(
                BedrockTabItem({ Text("Hello") }, { Text(text = "Hello") }),
                BedrockTabItem({ Text("World") }, { Text(text = "World") }),
            ),
        )
    }
}
