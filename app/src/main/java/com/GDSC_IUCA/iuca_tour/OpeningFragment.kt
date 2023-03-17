package com.GDSC_IUCA.iuca_tour

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.GDSC_IUCA.iuca_tour.databinding.FragmentOpeningBinding

class OpeningFragment : Fragment(R.layout.fragment_opening) {
    private lateinit var binding: FragmentOpeningBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOpeningBinding.bind(view)

//        binding.textView4.setOnClickListener {
//            Navigation.findNavController(view)
//                .navigate(R.id.action_openingFragment_to_languageFragment)
//        }


        // Shared preference
        val sharedPre = this.activity?.getSharedPreferences("pref", MODE_PRIVATE)
        val startFragment = sharedPre?.getInt("startFragment", 0)
        Log.e("START_FRAG", startFragment.toString())


        val SPLASH_TIME_OUT: Long = 1000



        if (startFragment == 1) {
            Navigation.findNavController(view)
                .navigate(R.id.action_openingFragment_to_secondFragment)

        } else {

            Handler().postDelayed(Runnable {
                Navigation.findNavController(view)
                    .navigate(R.id.action_openingFragment_to_languageFragment)
            }, SPLASH_TIME_OUT)
        }

    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }
}