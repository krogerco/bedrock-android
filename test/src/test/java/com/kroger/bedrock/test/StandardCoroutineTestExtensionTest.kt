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
package com.kroger.bedrock.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

internal class StandardCoroutineTestExtensionTest {

    internal class TestViewModel : ViewModel() {
        private val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
            // do nothing
        }

        init {
            viewModelScope.launch(coroutineExceptionHandler) { }
        }

        val viewState = MutableStateFlow(initialValue).asStateFlow()
    }

    @Test
    @ExtendWith(StandardCoroutineTestExtension::class)
    fun `Given a flow in a ViewModel launching scope in init, When the flow is accessed, Then it succeeds`() =
        runTest {
            val viewModel = TestViewModel()

            assertEquals(initialValue, viewModel.viewState.value)
        }

    @Test
    fun `Given a flow in a ViewModel launching scope in init, When created, Then an exception should be thrown`() =
        runTest {
            val exception = Assertions.assertThrows(IllegalStateException::class.java) {
                TestViewModel()
            }

            assertEquals(
                "Module with the Main dispatcher had failed to initialize. For tests Dispatchers.setMain from kotlinx-coroutines-test module can be used",
                exception.message,
            )
        }
}

internal const val initialValue: String = "ABCD"
