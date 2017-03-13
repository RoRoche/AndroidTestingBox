package com.guddy.android_testing_box.cucumber;

import com.guddy.android_testing_box.Sum;

import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SumSteps {
    Sum moSum;
    int miSum;

    @Given("^two int (-?\\d+) and (-?\\d+) to sum$")
    public void twoIntToSum(final int a, final int b) {
        moSum = new Sum(a, b);
    }

    @When("^computing sum$")
    public void computingSum() throws ConcurrentException {
        miSum = moSum.getSum();
    }

    @Then("^it should be (-?\\d+)$")
    public void itShouldBe(final int expected) {
        Assert.assertEquals(expected, miSum);
    }

}
