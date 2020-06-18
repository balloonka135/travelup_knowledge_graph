package com.example.travelupprototype

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelupprototype.adapters.PlacesRecyclerViewAdapter
import com.example.travelupprototype.models.Data
import com.example.travelupprototype.models.Place
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_places.*


class PlacesActivity : AppCompatActivity() {
    private var placesAdapter: PlacesRecyclerViewAdapter? = null
    private var verticalLayoutManager: LinearLayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_ACTION_BAR)
        val colorDrawable = ColorDrawable(Color.parseColor("#ffffff"))
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = colorDrawable.color
        setContentView(R.layout.activity_places)

        verticalLayoutManager = LinearLayoutManager(this)

        places_recycler_view.layoutManager = verticalLayoutManager
        back_btn?.setOnClickListener {
            finish()
        }

        var jsonFileString: String? = String()
        if (intent.hasExtra("search") && intent.getStringExtra("search") == "sports") {
            jsonFileString = Data.getJsonDataFromAsset(applicationContext, "sports.json")
        } else {
            jsonFileString = Data.getJsonDataFromAsset(applicationContext, "places.json")
        }

        val placeType = object : TypeToken<List<Place>>() {}.type

        var places = Gson().fromJson<List<Place>>(jsonFileString, placeType)
        placesAdapter = PlacesRecyclerViewAdapter(places)
        places_recycler_view.adapter = placesAdapter
        places_recycler_view.visibility = View.VISIBLE
    }
}