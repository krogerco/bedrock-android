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

import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

/**
 * A JUnit 5 extension for testing coroutines that mocks the main Dispatcher to run tasks
 * immediately. Note that this extension replaces the Main Dispatcher with [UnconfinedTestDispatcher]
 * which eagerly starts created coroutines on the current thread. This is useful when testing APIs
 * like [viewModelScope] that hardcode a Main dispatcher. If such APIs are not being used, it's
 * preferable to inject dispatchers instead and replace them all in the tests with the [TestDispatcher].
 *
 * See https://developer.android.com/kotlin/coroutines/test for more information on how to test coroutines.
 *
 * Add this to your test class as an extension:
 * ```
 * @ExtendWith(CoroutineTestExtension::class)
 *```
 *
 * Or alternatively, as a field:
 * ```
 * @JvmField
 * @RegisterExtension
 * val coroutinesTestExtension = CoroutineTestExtension()
 * ```
 */
@OptIn(ExperimentalCoroutinesApi::class)
public open class CoroutineTestExtension(
    public val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : AfterEachCallback,
    BeforeEachCallback {
    override fun beforeEach(context: ExtensionContext?) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun afterEach(context: ExtensionContext?) {
        Dispatchers.resetMain()
    }
}

/**
 * A JUnit 5 extension for testing coroutines that mocks the main Dispatcher to run tasks
 * immediately or with a delay. This differs from [CoroutineTestExtension] by replacing
 * the Main dispatcher with [StandardTestDispatcher] instead of [UnconfinedTestDispatcher].
 *
 * [StandardTestDispatcher] queues up coroutines that are started in the test context,
 * rather than entering them eagerly. This gives more control over the coroutines in the
 * test, but execution must be yielded to them before they will run.
 *
 * See https://developer.android.com/kotlin/coroutines/test#standardtestdispatcher
 *
 * Add this to your test class as an extension:
 * ```
 * @ExtendWith(StandardCoroutineTestExtension::class)
 *```
 *
 * Or alternatively, as a field:
 * ```
 * @JvmField
 * @RegisterExtension
 * val coroutinesTestExtension = StandardCoroutineTestExtension()
 * ```
 */
public open class StandardCoroutineTestExtension : CoroutineTestExtension(StandardTestDispatcher())

/**
 * A JUnit 5 extension for testing coroutines that uses mockk to replace all Dispatchers
 * calls with a testDispatcher, ensuring that all code will be run with the same dispatcher,
 * effectively putting all coroutines on the same thread running sequentially. This is especially
 * helpful when testing asynchronous functions as they will be run immediately on launch as if
 * a coroutine was never launched.
 *
 * Add it to your test class with
 * ```
 * @ExtendWith(CoroutineTestExtensionWithMockDispatchers::class)
 *```
 *
 * Or alternatively as a field
 * ```
 * @JvmField
 * @RegisterExtension
 * val coroutinesTestExtensionWithMockDispatchers = CoroutineTestExtensionWithMockDispatchers()
 * ```
 */
@ExperimentalCoroutinesApi
public class CoroutineTestExtensionWithMockDispatchers(
    testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : CoroutineTestExtension(testDispatcher) {
    override fun beforeEach(context: ExtensionContext?) {
        super.beforeEach(context)
        mockkStatic(Dispatchers::class)
        every { Dispatchers.IO } returns testDispatcher
        every { Dispatchers.Default } returns testDispatcher
        every { Dispatchers.Unconfined } returns testDispatcher
    }

    override fun afterEach(context: ExtensionContext?) {
        super.afterEach(context)
        unmockkStatic(Dispatchers::class)
    }
}
