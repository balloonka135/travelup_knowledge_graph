package com.example.travelupprototype.models

import android.content.Context
import com.google.gson.annotations.SerializedName
import java.io.IOException

data class Place(

    @SerializedName("name") val placename: String?, @SerializedName("tags") val description: String?,
    @SerializedName("rating") val rating: Float?, @SerializedName("imageUrl") val imageUrl: String?
)


object Data {

    var culturalPlaces = arrayListOf<Place>()


    fun getJsonDataFromAsset(context: Context, fileName: String = "assets/places.json"): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }


}




