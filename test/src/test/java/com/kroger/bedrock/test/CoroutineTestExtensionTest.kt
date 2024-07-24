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

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

internal class CoroutineTestExtensionTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    @ExtendWith(CoroutineTestExtension::class)
    fun `Given a viewmodel calling a dependency to launch a coroutine scope, When scope is launched with the Test Extension, Then the call succeeds`() =
        runTest {
            val fakeRepo = FakeRepo(UnconfinedTestDispatcher(testScheduler))
            val testViewModel = TestViewModel(fakeRepo)

            assertEquals(false, fakeRepo.fakeUpdateCalled)
            testViewModel.viewModelScopeTest()
            advanceUntilIdle()
            assertEquals(true, fakeRepo.fakeUpdateCalled)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Given a viewmodel calling a dependency to launch a coroutine scope, When scope is launched without the Test Extension, Then the expected result of true will actually return false`() = runTest {
        val fakeRepo = FakeRepo()
        val testViewModel = TestViewModel(fakeRepo)

        assertEquals(false, fakeRepo.fakeUpdateCalled)
        testViewModel.viewModelScopeTest()
        advanceUntilIdle()
        assertEquals(false, fakeRepo.fakeUpdateCalled)
    }
}

internal class TestViewModel(private val fakeRepo: FakeRepo) {
    private val testScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
        // do nothing
    }

    fun viewModelScopeTest() = testScope.launch(coroutineExceptionHandler) {
        fakeRepo.fakeUpdate()
    }
}

internal class FakeRepo(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {
    var fakeUpdateCalled = false
    suspend fun fakeUpdate() = withContext(dispatcher) {
        // delay to simulate a repo update taking time
        delay(500)
        fakeUpdateCalled = true
    }
}
