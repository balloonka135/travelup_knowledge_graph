package com.example.travelupprototype

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Color.parseColor
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_onboarding2.*


class OnBoarding2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_ACTION_BAR)
        val colorDrawable = ColorDrawable(parseColor("#2E2A72"))
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = colorDrawable.color
        setContentView(R.layout.activity_onboarding2)
        image2.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(0, 0)
    }


}