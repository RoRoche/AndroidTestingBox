package com.guddy.android_testing_box

import org.amshove.kluent.shouldBe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class KSumTest : Spek({

    given("two numbers a = 1 and b = 3") {
        val a: Int = 1
        val b: Int = 3

        on("computing the sum of these 2 numbers") {
            val sum: Sum = Sum(a, b)

            it("should compute sum = 4") {
                sum.sum shouldBe 4
            }
        }
    }
})