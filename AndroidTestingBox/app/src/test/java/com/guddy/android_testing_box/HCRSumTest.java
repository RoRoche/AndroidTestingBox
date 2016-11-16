package com.guddy.android_testing_box;

import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;

import static com.google.common.truth.Truth.assertThat;

@RunWith(HierarchicalContextRunner.class)
public class HCRSumTest {

    public class GivenTwoNumbers1And3 {
        private int a = 1;
        private int b = 3;

        @Before
        public void setUp() {
            a = 1;
            b = 3;
        }

        public class WhenComputingSum {
            private Sum sum;

            @Before
            public void setUp() {
                sum = new Sum(a, b);
            }

            @Test
            public void thenShouldBeEqualTo4() throws ConcurrentException {
                assertThat(sum.getSum()).isEqualTo(4);
            }
        }

        public class WhenMultiplying {
            private int multiply;

            @Before
            public void setUp() {
                multiply = a * b;
            }

            @Test
            public void thenShouldBeEqualTo3() throws ConcurrentException {
                assertThat(multiply).isEqualTo(3);
            }
        }
    }
}
