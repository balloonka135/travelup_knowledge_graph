package com.example.travelupprototype

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Color.parseColor
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_sign_in.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_ACTION_BAR)
        val colorDrawable = ColorDrawable(parseColor("#F86D5C"))
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = colorDrawable.color
        setContentView(R.layout.activity_sign_in)

        image.setOnClickListener {
            startActivity(Intent(this, OnBoarding1Activity::class.java))
            overridePendingTransition(0, 0)
        }


    }




}