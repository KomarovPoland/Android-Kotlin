package com.GDSC_IUCA.iuca_tour.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.GDSC_IUCA.iuca_tour.R
import com.GDSC_IUCA.iuca_tour.databinding.FragmentHallBinding


class MapFragment : Fragment(R.layout.fragment_hall) {
    private lateinit var binding: FragmentHallBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHallBinding.bind(view)

        binding.button3.setOnClickListener {
            val intent = Intent (activity, MainPageActivity::class.java)
            activity?.startActivity(intent)
        }
    }
}