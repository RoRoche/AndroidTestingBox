package com.guddy.android_testing_box

import com.google.common.truth.Truth.assertThat
import de.bechte.junit.runners.context.HierarchicalContextRunner
import org.apache.commons.lang3.concurrent.ConcurrentException
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(HierarchicalContextRunner::class)
class KHCRSumTest {

    inner class GivenTwoNumbers1And3 {
        private var a = 1
        private var b = 3

        @Before
        fun setUp() {
            a = 1
            b = 3
        }

        inner class WhenComputingSum {
            private var sum: Sum? = null

            @Before
            fun setUp() {
                sum = Sum(a, b)
            }

            @Test
            @Throws(ConcurrentException::class)
            fun thenShouldBeEqualTo4() {
                assertThat(sum!!.sum).isEqualTo(4)
            }
        }

        inner class WhenMultiplying {
            private var multiply: Int = 0

            @Before
            fun setUp() {
                multiply = a * b
            }

            @Test
            @Throws(ConcurrentException::class)
            fun thenShouldBeEqualTo3() {
                assertThat(multiply).isEqualTo(3)
            }
        }
    }
}
