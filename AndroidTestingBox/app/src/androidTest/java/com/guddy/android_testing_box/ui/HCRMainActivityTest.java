package com.guddy.android_testing_box.ui;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.guddy.android_testing_box.MainActivity;
import com.guddy.android_testing_box.R;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;

import static com.google.common.truth.Truth.assertThat;

@RunWith(HierarchicalContextRunner.class)
public class HCRMainActivityTest {

    public class GivenMainActivity {
        @Rule
        public final ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class, true, false);

        //region Fields
        private Solo mSolo;
        private MainActivity mActivity;
        private Context mContextTarget;
        //endregion

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

        public class WhenLaunchingActivity {
            @Before
            public void setUp() {
                mActivity = mActivityTestRule.launchActivity(null);
            }

            @Test
            public void thenItShouldDisplayAppName() {
                final boolean lbFoundTheRepo = mSolo.waitForText(mContextTarget.getString(R.string.app_name), 1, 5000L, true);
                assertThat(lbFoundTheRepo);
            }
        }
    }
}
