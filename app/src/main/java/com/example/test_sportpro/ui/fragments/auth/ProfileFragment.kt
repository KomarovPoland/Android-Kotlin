package com.example.test_sportpro.ui.fragments.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.test_sportpro.R
import com.example.test_sportpro.databinding.FragmentProfileBinding
import com.example.test_sportpro.ui.SportViewModel
import com.example.test_sportpro.ui.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    lateinit var navController: NavController
    lateinit var viewModel: SportViewModel
    var num = 19
    private var fragmentProfileBinding: FragmentProfileBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        navController = Navigation.findNavController(view)

        view.imageButtonJudge.setOnClickListener { // Тренет
            val action1 = ProfileFragmentDirections.actionProfileFragmentToNumberFragment("1")
            Navigation.findNavController(view).navigate(action1)

        }
        view.imageButtonTrainer.setOnClickListener { // Судья
            val action2 = ProfileFragmentDirections.actionProfileFragmentToNumberFragment("2")
            Navigation.findNavController(view).navigate(action2)

        }

        view.testButtonToMain.setOnClickListener {
            val action3 = ProfileFragmentDirections.actionProfileFragmentToMainProfileFragment()
            Navigation.findNavController(view).navigate(action3)
        }

        view.testButtonToRegistration.setOnClickListener {
            val action3 = ProfileFragmentDirections.actionProfileFragmentToRegisterFragment("")
            Navigation.findNavController(view).navigate(action3)
        }
//        view.findViewById<ImageButton>(R.id.imageButtonJudge).setOnClickListener(this)
//        view.findViewById<ImageButton>(R.id.imageButtonTrainer).setOnClickListener(this)
//        view.findViewById<Button>(R.id.testButton).setOnClickListener(this)

        fragmentProfileBinding = FragmentProfileBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel

    }

//    override fun onClick(v: View?) {
//        when (v!!.id) {
//
//            R.id.imageButtonJudge -> {
//                navController.navigate(R.id.action_profileFragment_to_mainProfileFragment)
////                navController.navigate(R.id.action_profileFragment_to_mainProfileFragment)
//
//
//            }
//            R.id.imageButtonTrainer -> navController.navigate(R.id.action_profileFragment_to_mainProfileFragment)
////            R.id.testButton -> navController.navigate((R.id.action_profileFragment_to_mainProfileFragment))
//
//
//        }
//    }
}
