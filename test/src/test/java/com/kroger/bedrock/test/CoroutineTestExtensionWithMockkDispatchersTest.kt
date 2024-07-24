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

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.Date

@OptIn(ExperimentalCoroutinesApi::class)
internal class CoroutineTestExtensionWithMockkDispatchersTest {
    @Test
    @ExtendWith(CoroutineTestExtensionWithMockDispatchers::class)
    fun `Given a coroutine test, When Run with mock dispatchers, Then it should advance automatically`() =
        runTest {
            val expectedDuration = 5000L
            val startTime: Long = Date().time

            val data = fetchData(expectedDuration)

            assertEquals("Hello world", data)
            val endTime: Long = Date().time
            val actualDuration = endTime - startTime
            assert(actualDuration < expectedDuration)
        }

    @Test
    fun `Given a coroutine test, When Run without mock dispatchers, Then it should not advance automatically`() =
        runTest {
            val expectedDuration = 25L
            val startTime: Long = Date().time

            val data = fetchData(expectedDuration)

            assertEquals("Hello world", data)
            val endTime: Long = Date().time
            val actualDuration = endTime - startTime
            assert(actualDuration > expectedDuration)
        }
}

private suspend fun fetchData(duration: Long): String = withContext(Dispatchers.IO) {
    delay(duration)
    "Hello world"
}
