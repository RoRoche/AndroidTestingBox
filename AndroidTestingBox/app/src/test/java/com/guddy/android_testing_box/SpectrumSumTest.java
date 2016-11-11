package com.guddy.android_testing_box;

import com.greghaskins.spectrum.Spectrum;

import org.junit.runner.RunWith;

import static com.google.common.truth.Truth.assertThat;
import static com.greghaskins.spectrum.Spectrum.describe;
import static com.greghaskins.spectrum.Spectrum.it;

@RunWith(Spectrum.class)
public class SpectrumSumTest {
    {
        describe("Given two numbers a = 1 and b = 3", () -> {
            final int a = 1;
            final int b = 3;

            it("computing the sum of these 2 numbers, should compute sum = 4", () -> {
                final Sum sum = new Sum(a, b);

                assertThat(sum.getSum()).isEqualTo(4);
            });
        });
    }
}