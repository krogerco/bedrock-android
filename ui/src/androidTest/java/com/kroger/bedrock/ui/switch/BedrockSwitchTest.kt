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
package com.kroger.bedrock.ui.switch

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class BedrockSwitchTest {
    @Rule
    @JvmField
    val composeTestRule: ComposeContentTestRule = createComposeRule()

    @Test
    fun bedrockSwitchIsDisplayedAndFunctions() {
        var isChecked = false
        composeTestRule.setContent {
            BedrockSwitch(checked = false, onCheckedChange = { isChecked = it })
        }

        val bedrockSwitch = composeTestRule.onNodeWithTag(SWITCH_TEST_TAG)

        bedrockSwitch.assertIsDisplayed()
        bedrockSwitch.assertIsOff()

        assert(!isChecked)

        bedrockSwitch.performClick()

        assert(isChecked)
    }

    @Test
    fun bedrockSwitchIsChecked() {
        var isChecked = true
        composeTestRule.setContent {
            BedrockSwitch(
                checked = isChecked,
                onCheckedChange = { isChecked = it },
            )
        }

        val bedrockSwitch = composeTestRule.onNodeWithTag(SWITCH_TEST_TAG)

        bedrockSwitch.assertIsDisplayed()
        bedrockSwitch.assertIsOn()

        assert(isChecked)

        bedrockSwitch.performClick()

        assert(!isChecked)
    }
}
