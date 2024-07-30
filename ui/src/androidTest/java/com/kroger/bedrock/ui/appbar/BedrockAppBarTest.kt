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

import android.content.Context
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.kroger.bedrock.ui.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class BedrockAppBarTest {
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Rule
    @JvmField
    val composeTestRule: ComposeContentTestRule = createComposeRule()

    @Test
    fun backButtonAndTitleAndIconAreDisplayed() {
        val titleString = "Test Title"
        val contentDescriptionString = "Androids R Best"
        composeTestRule.setContent {
            BedrockAppBar(
                title = { Text(text = titleString) },
                navigationIcon = { UpButton { } },
                appBarActions = {
                    PreviewAppBarIcon(
                        imageVector = Icons.Default.Android,
                        contentDescriptionString,
                    )
                },
            ) { }
        }

        val title = composeTestRule.onNodeWithText(titleString)
        val backButton =
            composeTestRule.onNodeWithContentDescription(context.resources.getString(R.string.content_description_up_button))
        val androidButton =
            composeTestRule.onNodeWithContentDescription(contentDescriptionString)

        title.assertIsDisplayed()
        backButton.assertIsDisplayed()
        androidButton.assertIsDisplayed()
    }
}
