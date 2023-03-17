package com.GDSC_IUCA.iuca_tour

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import com.GDSC_IUCA.iuca_tour.databinding.FragmentLanChoseBinding
import kotlinx.android.synthetic.main.fragment_second.*
import org.w3c.dom.Text

class LanChoseFragment : Fragment(R.layout.fragment_lan_chose) {
    private lateinit var binding: FragmentLanChoseBinding

    private lateinit var textView: TextView

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//
//        val view: View = inflater.inflate(R.layout.fragment_lan_chose, container,false)
//        textView = view.findViewById<View>(R.id.text_lan_chose) as TextView
//        val bundle = arguments
//        val message = bundle!!.getString("message")
//        textView.text = message
//        // Inflate the layout for this fragment
//        return view
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLanChoseBinding.bind(view)
//        val bundle = arguments
//        val message = bundle!!.getString("message")
//        binding.textView5.text = message


        binding.rusBtn.setOnClickListener {
            val action = LanguageFragmentDirections.actionLanguageFragmentToSecondFragment("RUS")
            Navigation.findNavController(view)
                .navigate(action)
        }

        binding.engBtn.setOnClickListener {
            val activity = activity as MainActivity
            activity.onBackPressed()
        }

        binding.chBtn.setOnClickListener {
            val action = LanguageFragmentDirections.actionLanguageFragmentToSecondFragment("CH")
            Navigation.findNavController(view)
                .navigate(action)
        }

        binding.kgBtn.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_languageFragment_to_secondFragment)
        }
    }

}