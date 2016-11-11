package com.guddy.android_testing_box;

import org.frutilla.annotations.Frutilla;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.google.common.truth.Truth.assertThat;

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
        final int a;
        final int b;
        Given:
        {
            a = 1;
            b = 3;
        }

        final Sum sum;
        When:
        {
            sum = new Sum(a, b);
        }

        Then:
        {
            assertThat(sum.getSum()).isEqualTo(4);
        }
    }
}