package com.beingmomin.mominapp.test

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.beingmomin.mominapp.R
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        Log.d("az","Activity 1 OnCreate")

        button_to_activity_2.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, Main3Activity::class.java))
        })

        button.setOnClickListener(View.OnClickListener {
            finish()
        })

    }

    override fun onStart() {
        super.onStart()
        Log.d("az","Activity 1 OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("az","Activity 1 OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("az","Activity 1 OnPause")
    }


    override fun onStop() {
        super.onStop()
        Log.d("az","Activity 1 OnStop")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d("az","Activity 1 OnDestroy")
    }
}
