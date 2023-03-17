package com.GDSC_IUCA.iuca_tour

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.GDSC_IUCA.iuca_tour.databinding.FragmentLanguageBinding


class LanguageFragment : Fragment(R.layout.fragment_language) {
    private lateinit var binding: FragmentLanguageBinding


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLanguageBinding.bind(view)

        val sharedPre = this.activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = sharedPre?.edit()




        binding.rusBtn.setOnClickListener {
            editor?.apply {
                putString("lang", "RUS")
            }?.apply()


            val langButton: ImageView = requireActivity().findViewById<ImageView>(R.id.lngBtnTopBar)
            langButton.setImageResource(R.drawable.flag_ru)


            val action = LanguageFragmentDirections.actionLanguageFragmentToSecondFragment("RUS")
            Navigation.findNavController(view)
                .navigate(action)
        }

        binding.engBtn.setOnClickListener {
            editor?.apply {
                putString("lang", "ENG")
            }?.apply()

            val  action = LanguageFragmentDirections.actionLanguageFragmentToSecondFragment("ENG")
            Navigation.findNavController(view)
                .navigate(action)

            val langButton: ImageView = requireActivity().findViewById<ImageView>(R.id.lngBtnTopBar)
            langButton.setImageResource(R.drawable.flag_us)

        }

        binding.chBtn.setOnClickListener {
//            val langButton: ImageView = requireActivity().findViewById<ImageView>(R.id.lngBtnTopBar)
//            langButton.setImageResource(R.drawable.flag_ch)
//
//            val action = LanguageFragmentDirections.actionLanguageFragmentToSecondFragment("CHN")
//            Navigation.findNavController(view)
//                .navigate(action)
            val action = LanguageFragmentDirections.actionLanguageFragmentToNotifyFragment()
            Navigation.findNavController(view)
                .navigate(action)
        }

        binding.kgBtn.setOnClickListener {
//            val langButton: ImageView = requireActivity().findViewById<ImageView>(R.id.lngBtnTopBar)
//            langButton.setImageResource(R.drawable.flag_kg)
            val action = LanguageFragmentDirections.actionLanguageFragmentToNotifyFragment()
            Navigation.findNavController(view)
                .navigate(action)

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