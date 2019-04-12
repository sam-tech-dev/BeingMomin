package com.beingmomin.mominapp.test

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.beingmomin.mominapp.R
import kotlinx.android.synthetic.main.activity_main3.*

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        Log.d("az","Activity 2 OnCreate")

        button_to_activity1 .setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, Main2Activity::class.java))
        })




    }

    override fun onStart() {
        super.onStart()
        Log.d("az","Activity 2 OnStart")
    }


    override fun onResume() {
        super.onResume()
        Log.d("az","Activity 2 OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("az","Activity 2 OnPause")
    }


    override fun onStop() {
        super.onStop()
        Log.d("az","Activity 2 OnStop")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d("az","Activity 2 OnDestroy")
    }


}
