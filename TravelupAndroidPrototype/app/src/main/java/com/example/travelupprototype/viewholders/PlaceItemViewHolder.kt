package com.example.travelupprototype.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travelupprototype.R
import com.example.travelupprototype.models.Place
import com.squareup.picasso.Picasso

class PlaceItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name = itemView.findViewById<TextView>(R.id.place_name)
    val description = itemView.findViewById<TextView>(R.id.description)
    val rating = itemView.findViewById<TextView>(R.id.rating)
    val place_image = itemView.findViewById<ImageView>(R.id.place_image)

    fun setItem(place: Place) {
        if (place.imageUrl?.isNotEmpty() == true) {
            Picasso.get().load(place.imageUrl).into(place_image)
        }
        name.text = place.placename
        description.text = place.description
        rating.text = "Rating: " + place.rating + "/5"
    }

}