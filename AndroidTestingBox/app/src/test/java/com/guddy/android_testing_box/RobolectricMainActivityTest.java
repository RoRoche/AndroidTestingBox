package com.guddy.android_testing_box;

import android.widget.Button;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.google.common.truth.Truth.assertThat;
import static com.guddy.android_testing_box.bdd.BddCanvas.given;
import static com.guddy.android_testing_box.bdd.BddCanvas.then;
import static com.guddy.android_testing_box.bdd.BddCanvas.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class RobolectricMainActivityTest {

    @Test
    public void test_clickingButton_shouldChangeText() throws Exception {

        given("The MainActivity", () -> {
            final MainActivity loActivity = Robolectric.setupActivity(MainActivity.class);
            final Button loButton = (Button) loActivity.findViewById(R.id.ActivityMain_Button);
            final TextView loTextView = (TextView) loActivity.findViewById(R.id.ActivityMain_TextView);

            when("clicking on the button", () -> {
                loButton.performClick();

                then("text should have changed", () -> assertThat(loTextView.getText().toString()).isEqualTo("Text changed after button click"));
            });
        });
    }

}
