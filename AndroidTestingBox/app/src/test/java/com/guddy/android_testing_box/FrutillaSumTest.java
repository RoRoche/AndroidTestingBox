package com.guddy.android_testing_box;

import org.frutilla.annotations.Frutilla;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.google.common.truth.Truth.assertThat;
import static com.guddy.android_testing_box.bdd.BddCanvas.given;
import static com.guddy.android_testing_box.bdd.BddCanvas.then;
import static com.guddy.android_testing_box.bdd.BddCanvas.when;

@RunWith(value = org.frutilla.FrutillaTestRunner.class)
public class FrutillaSumTest {

    @Frutilla(
            Given = "two numbers a = 1 and b = 3",
            When = "computing the sum of these 2 numbers",
            Then = "should compute sum = 4"
    )
    @Test
    public void addition_isCorrect() throws Exception {
        given("two numbers", () -> {
            final int a = 1;
            final int b = 3;

            when("computing the sum of these 2 numbers", () -> {
                final Sum sum = new Sum(a, b);

                then("should compute sum = 4", () -> assertThat(sum.getSum()).isEqualTo(4));
            });
        });
    }
}