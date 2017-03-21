package com.guddy.android_testing_box.ui;

import android.test.ActivityInstrumentationTestCase2;

import com.guddy.android_testing_box.MainActivity;
import com.guddy.android_testing_box.R;

import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@CucumberOptions(features = "features")
public class CucumberMainActivitySteps extends ActivityInstrumentationTestCase2<MainActivity> {

    public CucumberMainActivitySteps() {
        super(MainActivity.class);
    }

    @Given("^the initial state is shown$")
    public void the_initial_main_activity_is_shown() {
        // Call the activity before each test.
        getActivity();
    }

    @When("^clicking on the button$")
    public void clicking_the_Click_Me_button() {
        onView(withId(R.id.ActivityMain_Button)).perform(click());
    }

    @Then("^the text changed to \"([^\"]*)\"$")
    public void text_$_is_shown(final String s) {
        onView(withId(R.id.ActivityMain_TextView)).check(matches(withText(s)));
    }
}
