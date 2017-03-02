package com.guddy.android_testing_box.ui;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.guddy.android_testing_box.MainActivity;
import com.guddy.android_testing_box.R;
import com.tngtech.jgiven.CurrentStep;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.Quoted;
import com.tngtech.jgiven.annotation.ScenarioState;
import com.tngtech.jgiven.attachment.Attachment;
import com.tngtech.jgiven.attachment.MediaType;
import com.tngtech.jgiven.integration.android.AndroidJGivenTestRule;
import com.tngtech.jgiven.junit.SimpleScenarioTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class EspressoJGivenMainActivityTest extends
        SimpleScenarioTest<EspressoJGivenMainActivityTest.Steps> {

    @Rule
    @ScenarioState
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public AndroidJGivenTestRule androidJGivenTestRule = new AndroidJGivenTestRule(getScenario());

    @Test
    public void clicking_ClickMe_changes_the_text() {
        given().the_initial_main_activity_is_shown()
                .with().text("AndroidTestingBox");
        when().clicking_the_Click_Me_button();
        then().text_$_is_shown("Text changed after button click");
    }

    public static class Steps extends Stage<Steps> {
        @ScenarioState
        CurrentStep currentStep;

        @ScenarioState
        ActivityTestRule<MainActivity> activityTestRule;

        public Steps the_initial_main_activity_is_shown() {
            // nothing to do, just for reporting
            return this;
        }

        public Steps clicking_the_Click_Me_button() {
            onView(withId(R.id.ActivityMain_Button)).perform(click());
            return this;
        }

        public Steps text(@Quoted String s) {
            return text_$_is_shown(s);
        }

        public Steps text_$_is_shown(@Quoted String s) {
            onView(withId(R.id.ActivityMain_TextView)).check(matches(withText(s)));
            takeScreenshot();
            return this;
        }

        private void takeScreenshot() {
            currentStep.addAttachment(
                    Attachment.fromBinaryBytes(ScreenshotUtils.takeScreenshot(activityTestRule.getActivity()), MediaType.PNG)
                            .showDirectly());
        }
    }
}
