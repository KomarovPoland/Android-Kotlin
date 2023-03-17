package com.example.test_sportpro.ui.fragments.news

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.test_sportpro.R
import com.example.test_sportpro.databinding.FragmentFilterBinding


class FilterFragment : Fragment(R.layout.fragment_filter) {
    private var fragmentFilterBinding : FragmentFilterBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        fragmentFilterBinding = FragmentFilterBinding.bind(view)

        fragmentFilterBinding!!.national.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("title", fragmentFilterBinding!!.textNational.text.toString().trim())
            bundle.putInt("id", 4)
            findNavController().navigate(R.id.action_filterFragment_to_sportTypesFragment, bundle)
        }

        fragmentFilterBinding!!.olympic.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("title", fragmentFilterBinding!!.textOlympic.text.toString().trim())
            bundle.putInt("id", 1)
            findNavController().navigate(R.id.action_filterFragment_to_sportTypesFragment, bundle)
        }

        fragmentFilterBinding!!.nonOlympic.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("title", fragmentFilterBinding!!.textNonOlympic.text.toString().trim())
            bundle.putInt("id", 2)
            findNavController().navigate(R.id.action_filterFragment_to_sportTypesFragment, bundle)
        }

        fragmentFilterBinding!!.paraOlympic.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("title", fragmentFilterBinding!!.textParaOlympic.text.toString().trim())
            bundle.putInt("id", 3)
            findNavController().navigate(R.id.action_filterFragment_to_sportTypesFragment, bundle)
        }
    }
}