package com.guddy.android_testing_box.ui;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.guddy.android_testing_box.MainActivity;
import com.guddy.android_testing_box.R;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.google.common.truth.Truth.assertThat;
import static com.guddy.android_testing_box.bdd.BddCanvas.given;
import static com.guddy.android_testing_box.bdd.BddCanvas.then;
import static com.guddy.android_testing_box.bdd.BddCanvas.when;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    //region Rule
    @Rule
    public final ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class, true, false);
    //endregion

    //region Fields
    private Solo mSolo;
    private MainActivity mActivity;
    private Context mContextTarget;
    //endregion

    //region Test lifecycle
    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
        mSolo = new Solo(InstrumentationRegistry.getInstrumentation(), mActivity);
        mContextTarget = InstrumentationRegistry.getTargetContext();
    }

    @After
    public void tearDown() throws Exception {
        mSolo.finishOpenedActivities();
    }
    //endregion

    //region Test methods
    @Test
    public void testTextDisplayed() throws Exception {
        given("the main activity", () -> {

            when("launching activity", () -> {
                mActivity = mActivityTestRule.launchActivity(null);

                then("should display 'app_name'", () -> {
                    final boolean lbFoundAppName = mSolo.waitForText(mContextTarget.getString(R.string.app_name), 1, 5000L, true);
                    assertThat(lbFoundAppName);
                });
            });
        });
    }
    //endregion
}
