package com.GDSC_IUCA.iuca_tour

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.GDSC_IUCA.iuca_tour.databinding.FragmentNotifyBinding


class NotifyFragment : Fragment(R.layout.fragment_notify) {
    private lateinit var binding: FragmentNotifyBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotifyBinding.bind(view)

        binding.choseOtherLng.setOnClickListener {
            val action = NotifyFragmentDirections.actionNotifyFragmentToLanguageFragment2()
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