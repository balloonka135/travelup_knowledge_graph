package com.example.travelupprototype.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travelupprototype.R
import com.example.travelupprototype.models.Place
import com.example.travelupprototype.viewholders.PlaceItemViewHolder

class PlacesRecyclerViewAdapter(val itemList: List<Place>) : RecyclerView.Adapter<PlaceItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false)
        return PlaceItemViewHolder(itemView)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: PlaceItemViewHolder, position: Int) {
        holder.setItem(itemList[position])
    }

}