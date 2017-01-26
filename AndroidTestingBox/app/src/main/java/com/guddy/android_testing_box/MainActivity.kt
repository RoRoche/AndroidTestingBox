package com.guddy.android_testing_box

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.ActivityMain_TextView) as TextView
        val button = findViewById(R.id.ActivityMain_Button)
        button.setOnClickListener({ view: View -> textView.setText(R.string.text_changed_after_button_click) })
    }
}
