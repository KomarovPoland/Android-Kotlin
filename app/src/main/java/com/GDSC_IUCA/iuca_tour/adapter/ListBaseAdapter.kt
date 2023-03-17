package com.GDSC_IUCA.iuca_tour.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

import com.GDSC_IUCA.iuca_tour.R
import com.GDSC_IUCA.iuca_tour.databinding.ItemNavPlaceBinding
import com.GDSC_IUCA.iuca_tour.models.PlacesItem
import com.GDSC_IUCA.iuca_tour.ui.MainPageFragmentDirections
import java.security.AccessControlContext
import java.security.AccessController.getContext


class ListBaseAdapter(
    private val placeItem2: List<PlacesItem>,
    private val listPlaces: ArrayList<String>,
    private val curPlace: Int
) : BaseAdapter() {

    override fun getItem(position: Int): String {
        return listPlaces[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listPlaces.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = convertView?.tag as ItemNavPlaceBinding? ?: createBinding(parent!!.context)


        val item = getItem(position)
        val currName = listPlaces[curPlace]

        if (position == curPlace) {
            binding.textIndication.setTextColor(Color.parseColor("#6066E4"))
            binding.imageIndication.setImageResource(R.drawable.ic_plase_burger_purple)
        }
        if (position < curPlace){
            binding.imageIndication.setImageResource(R.drawable.ic_passed_blue_burger)
            binding.textIndication.setTextColor(Color.parseColor("#161616"))

        }
        if (position > curPlace){
            binding.imageIndication.setImageResource(R.drawable.ic_passive_places_burger)
            binding.textIndication.setTextColor(Color.parseColor("#A19999"))

        }

        binding.textIndication.text = item

        return binding.root
    }

    private fun createBinding(context: Context): ItemNavPlaceBinding {
        val binding = ItemNavPlaceBinding.inflate(LayoutInflater.from(context))
        binding.root.tag = binding
        return binding
    }
}