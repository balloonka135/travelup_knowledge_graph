package com.example.travelupprototype

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_city_search.*

class CitySearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_ACTION_BAR)
        val colorDrawable = ColorDrawable(Color.parseColor("#ffffff"))
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = colorDrawable.color
        setContentView(R.layout.activity_city_search)

        setupSearchView()

        back_btn?.setOnClickListener {
            finish()
        }

        cultural?.setOnClickListener {
            var intent = Intent(this@CitySearchActivity, PlacesActivity::class.java)
            startActivity(intent)
        }
    }

    fun setupSearchView() {
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                var intent = Intent(this@CitySearchActivity, PlacesActivity::class.java)
                intent.putExtra("search", query?.trim()?.toLowerCase())
                startActivity(intent)
                return true
            }
        })
    }
}