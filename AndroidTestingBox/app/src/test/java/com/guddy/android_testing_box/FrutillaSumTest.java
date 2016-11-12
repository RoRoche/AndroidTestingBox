package com.guddy.android_testing_box;

import com.guddy.android_testing_box.bdd.Given;
import com.guddy.android_testing_box.bdd.Then;
import com.guddy.android_testing_box.bdd.When;

import org.frutilla.annotations.Frutilla;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.google.common.truth.Truth.assertThat;
import static com.guddy.android_testing_box.bdd.Given.given;
import static com.guddy.android_testing_box.bdd.When.when;
import static com.guddy.android_testing_box.bdd.Then.then;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(value = org.frutilla.FrutillaTestRunner.class)
public class FrutillaSumTest {

    @Frutilla(
            Given = "two numbers a = 1 and b = 3",
            When = "computing the sum of these 2 numbers",
            Then = "should compute sum = 4"
    )
    @Test
    public void addition_isCorrect() throws Exception {
        given("two numbers", new Given(() -> {
            final int a = 1;
            final int b = 3;

            when("computing the sum of these 2 numbers", new When(() ->{
                final Sum sum = new Sum(a, b);

                then("should compute sum = 4", new Then(() -> {
                    assertThat(sum.getSum()).isEqualTo(4);
                }));
            }));
        }));
    }
}