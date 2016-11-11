package com.example.fx12946.myapplication

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.guddy.android_testing_box.MainActivity
import com.guddy.android_testing_box.R
import com.robotium.solo.Solo
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class KMainActivityTest() {
    //region Rule
    @get:Rule
    val mActivityTestRule = ActivityTestRule(MainActivity::class.java, true, false)
    //endregion

    private var mActivity: MainActivity? = null
    private var mSolo: Solo? = null
    private var mContextTarget: Context? = null
    //endregion

    //region Test lifecycle
    @Before
    @Throws(Exception::class)
    fun setUp() {
        mActivity = mActivityTestRule.activity
        mSolo = Solo(InstrumentationRegistry.getInstrumentation(), mActivity)
        mContextTarget = InstrumentationRegistry.getTargetContext()
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        mSolo!!.finishOpenedActivities()
    }
    //endregion

    //region Test methods
    @Test
    fun testTextDisplayed() {
        mActivity = mActivityTestRule.launchActivity(null)

        val lbFoundTheRepo = mSolo!!.waitForText(mContextTarget!!.getString(R.string.app_name), 1, 5000L, true)
        assertTrue(lbFoundTheRepo)
    }
    //endregion
}
