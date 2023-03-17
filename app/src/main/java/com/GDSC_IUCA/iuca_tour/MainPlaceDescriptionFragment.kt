package com.GDSC_IUCA.iuca_tour

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.GDSC_IUCA.iuca_tour.databinding.FragmentMainPlaceDescriptionBinding


class MainPlaceDescriptionFragment : Fragment(R.layout.fragment_main_place_description) {
    private lateinit var binding:FragmentMainPlaceDescriptionBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainPlaceDescriptionBinding.bind(view)

    }
}