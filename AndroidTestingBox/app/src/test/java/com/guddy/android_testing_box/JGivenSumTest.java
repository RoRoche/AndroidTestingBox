package com.guddy.android_testing_box;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.junit.SimpleScenarioTest;

import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class JGivenSumTest extends SimpleScenarioTest<JGivenSumTest.TestSteps> {

    @Test
    public void addition_isCorrect() throws ConcurrentException {
        given().first_number_$(1).and().second_number_$(3);
        when().computing_sum();
        then().it_should_be_$(4);
    }

    public static class TestSteps extends Stage<TestSteps> {
        private int mA;
        private int mB;
        private Sum mSum;

        public TestSteps first_number_$(final int piA) {
            mA = piA;
            return this;
        }

        public void second_number_$(final int piB) {
            mB = piB;
        }

        public void computing_sum() {
            mSum = new Sum(mA, mB);
        }

        public void it_should_be_$(final int piExpected) throws ConcurrentException {
            assertThat(mSum.getSum()).isEqualTo(piExpected);
        }
    }
}
