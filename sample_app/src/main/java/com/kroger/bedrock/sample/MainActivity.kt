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
package com.kroger.bedrock.sample

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.kroger.bedrock.ui.BedrockTheme
import com.kroger.bedrock.ui.appbar.BedrockAppBar
import com.kroger.bedrock.ui.favorite.BedrockFavorite
import com.kroger.bedrock.ui.favorite.BedrockIconToggle
import com.kroger.bedrock.ui.switch.BedrockSwitch
import com.kroger.bedrock.ui.tabs.BedrockTabItem
import com.kroger.bedrock.ui.tabs.BedrockTabs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }
}

@Composable
private fun MainContent() {
    val context = LocalContext.current
    var isFavorited: Boolean by remember { mutableStateOf(false) }
    var isFlagChecked: Boolean by rememberSaveable { mutableStateOf(false) }

    BedrockTheme {
        BedrockAppBar(title = { Text(text = "Bedrock") }, appBarActions = { AndroidMenuButton {} }) {
            BedrockTabs(
                modifier = Modifier.fillMaxSize(),
                tabItems = listOf(
                    BedrockTabItem({ Text("Home") }, { DemoAppScreen() }),
                    BedrockTabItem(
                        { Text("Icon Toggles & Switch") },
                        {
                            FavoritesTabContent(
                                isFavorited,
                                isFlagChecked,
                                {
                                    isFavorited = it
                                    toast(context, "Favorite Pressed! Selected: $it")
                                },
                                {
                                    isFlagChecked = it
                                    toast(context, "Star Pressed! Selected: $it")
                                },
                            )
                        },
                    ),
                ),
            )
        }
    }
}

@Composable
private fun FavoritesTabContent(
    isFavorited: Boolean,
    isFlagChecked: Boolean,
    onFavoriteChecked: (Boolean) -> Unit,
    onFlagChecked: (Boolean) -> Unit,
) {
    var isSwitchChecked: Boolean by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(BedrockTheme.dimensions.gutter),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        BedrockFavorite(
            isFavorited = isFavorited,
            contentDescription = "Favorite Sample",
            onPressed = onFavoriteChecked,
        )
        BedrockIconToggle(
            isChecked = isFlagChecked,
            modifier = Modifier.padding(top = BedrockTheme.dimensions.gutter),
            checkedIcon = Icons.Default.Star,
            uncheckedIcon = Icons.Default.StarBorder,
            contentDescription = "Icon Toggle with Flag",
            onPressed = onFlagChecked,
        )
        BedrockSwitch(
            checked = isSwitchChecked,
            onCheckedChange = { isSwitchChecked = !isSwitchChecked },
        )
    }
}

@Composable
private fun AndroidMenuButton(onAndroidMenuButtonPressed: () -> Unit) {
    IconButton(
        onClick = onAndroidMenuButtonPressed,
        content = {
            Icon(
                imageVector = Icons.Default.Android,
                contentDescription = "Android Menu Button",
            )
        },
    )
}

private fun toast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MainContentPreview() {
    MainContent()
}
