package com.GDSC_IUCA.iuca_tour

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.GDSC_IUCA.iuca_tour.databinding.FragmentTimeBinding

class TimeFragment : Fragment(R.layout.fragment_time) {
    private lateinit var binding: FragmentTimeBinding
    private val args: TimeFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTimeBinding.bind(view)
        val lng = args.lng

        binding.btnShortTour.setOnClickListener {
            val action = TimeFragmentDirections.actionTimeFragmentToStartExcurtionFragment(lng, 1)
            Navigation.findNavController(view).navigate(action)
        }

        binding.btnLongTour.setOnClickListener {
            val action = TimeFragmentDirections.actionTimeFragmentToStartExcurtionFragment(lng, 2)
            Navigation.findNavController(view).navigate(action)
        }
    }
}