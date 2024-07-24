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
package com.kroger.bedrock.util.coroutine

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalCoroutinesApi::class)
internal class ConcurrencyHelpersTest {
    @Test
    fun `GIVEN two tasks using joinPreviousOrRun WHEN task runs are disjoint THEN each task returns its own result`() = runTest {
        val runner = ControlledRunner<Int>()
        val num1 = runner.joinPreviousOrRun { 1 }
        val num2 = runner.joinPreviousOrRun { 2 }

        assertThat(num1).isEqualTo(1)
        assertThat(num2).isEqualTo(2)
    }

    @Test
    fun `GIVEN two tasks using joinPreviousOrRun WHEN task runs intersect THEN both tasks return same result`() = runTest {
        val runner = ControlledRunner<Int>()
        val result1 = async {
            runner.joinPreviousOrRun {
                delay(2.seconds)
                1
            }
        }

        val result2 = async {
            runner.joinPreviousOrRun { 2 }
        }

        val (num1, num2) = awaitAll(result1, result2)
        assertThat(num1).isEqualTo(1)
        assertThat(num2).isEqualTo(1)
    }

    @Test
    fun `GIVEN two tasks using cancelPreviousThenRun WHEN task runs intersect THEN first task is cancelled and second task result is returned`() = runTest {
        val runner = ControlledRunner<Int>()
        val result1 = async {
            runner.cancelPreviousThenRun {
                delay(2.seconds)
                1
            }
        }

        val result2 = async {
            runner.cancelPreviousThenRun { 2 }
        }

        assertThrows<CancellationException> { result1.await() }
        val num2 = result2.await()
        assertThat(num2).isEqualTo(2)
    }

    @Test
    fun `GIVEN two tasks using afterPrevious WHEN tasks scheduled in intersecting way THEN second task does not start until after first task completes`() = runTest {
        val runner = SingleRunner()
        var currentState: String? = null
        val job1 = launch {
            runner.afterPrevious {
                currentState = "executing 1"
                delay(2.seconds)
                currentState = "completed 1"
            }
        }

        advanceTimeBy(1000)
        assertThat(currentState).isEqualTo("executing 1")

        val job2 = launch {
            currentState = "queuing 2"
            runner.afterPrevious {
                assertThat(currentState).isEqualTo("completed 1")
                currentState = "completed 2"
            }
        }

        advanceTimeBy(500)
        assertThat(currentState).isEqualTo("queuing 2")

        joinAll(job1, job2)
        currentState = "completed 2"
    }
}
