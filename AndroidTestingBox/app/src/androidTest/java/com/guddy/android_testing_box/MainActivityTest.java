package com.guddy.android_testing_box;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.robotium.solo.Solo;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

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
    public void testTextDisplayed() {
        Given:
        {
            mActivity = mActivityTestRule.launchActivity(null);
        }

        When:
        {
        }

        Then:
        {
            final boolean lbFoundTheRepo = mSolo.waitForText(mContextTarget.getString(R.string.app_name), 1, 5000L, true);
            assertTrue(lbFoundTheRepo);
        }
    }
    //endregion
}
