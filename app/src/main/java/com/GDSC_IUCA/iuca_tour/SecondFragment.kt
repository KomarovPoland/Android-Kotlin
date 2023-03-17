package com.GDSC_IUCA.iuca_tour

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.GDSC_IUCA.iuca_tour.databinding.FragmentSecondBinding


class SecondFragment : Fragment(R.layout.fragment_second) {
    val args: SecondFragmentArgs by navArgs()

    private lateinit var binding: FragmentSecondBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSecondBinding.bind(view)

        val lng = args.lng
        binding.button.setOnClickListener {
            val action = SecondFragmentDirections.actionSecondFragmentToTimeFragment(lng)
            Navigation.findNavController(view).navigate(action)
        }

        // Shared preference
        val sharedPre = this.activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = sharedPre?.edit()

        editor?.apply {
            putInt("startFragment", 1)
        }?.apply()



    }


}