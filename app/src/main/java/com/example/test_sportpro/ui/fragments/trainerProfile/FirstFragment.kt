package com.example.test_sportpro.ui.fragments.trainerProfile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.test_sportpro.R
import com.example.test_sportpro.databinding.FragmentFirstBinding
import com.example.test_sportpro.models.UserItem
import com.example.test_sportpro.ui.SportViewModel
import com.example.test_sportpro.ui.activities.MainActivity
import com.example.test_sportpro.utils.Resource
import com.example.test_sportpro.utils.SessionManager
import kotlinx.android.synthetic.main.fragment_first.*


class FirstFragment : Fragment(R.layout.fragment_first) {

    private var fragmentFirstBinding : FragmentFirstBinding? = null
    lateinit var viewModel: SportViewModel
    private val TAG = "TAG_first"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentFirstBinding = FragmentFirstBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel

        val sessionManager = SessionManager(requireContext())

        val number = sessionManager.fetchPhone()

        if (findNavController().previousBackStackEntry?.arguments?.getSerializable("user") != null) {
            val user =
                findNavController().previousBackStackEntry?.arguments?.getSerializable("user") as UserItem

            val regionString = when (user.region) {
                1 -> "Чуйская область"
                2 -> "Ошская область"
                3 -> "Баткенская область"
                4 -> "Джалал-Абадская область"
                5 -> "Таласская область"
                6 -> "Нарынская область"
                7 -> "Иссык-Кульская область"
                else -> "Иссык-Кульская область"
            }

            val sportString = when (user.sport) {
                1 -> "Дзюдо"
                2 -> "Көк бөрү"
                3 -> "Борьба"
                4 -> "Айкидо"
                5 -> "Ушу"
                else -> "Дзюдо"
            }


            fragmentFirstBinding!!.surname.setText(user.surname)
            fragmentFirstBinding!!.name.setText(user.name)
            fragmentFirstBinding!!.middleName.setText(user.middlename)
            fragmentFirstBinding!!.region.setText(regionString)
            fragmentFirstBinding!!.phoneNumber.setText(user.phone)
            fragmentFirstBinding!!.sportType.setText(sportString)
        } else {

            viewModel.getTrainers()

            viewModel.trainers.observe(viewLifecycleOwner, { response ->
                when (response) {
                    is Resource.Success -> {
                        response.message?.let { Log.d("TAG_SUCCESS", it) }

                        response.data?.let { user ->
                            for (i in user) {
                                if (i.user.phone == number) {

//                                Toast.makeText(activity, i.user.toString(), Toast.LENGTH_LONG)
//                                    .show()

                                    Log.d("TAG_User", i.user.toString())

                                    val regionString = when (i.user.region) {
                                        1 -> "Чуйская область"
                                        2 -> "Ошская область"
                                        3 -> "Баткенская область"
                                        4 -> "Джалал-Абадская область"
                                        5 -> "Таласская область"
                                        6 -> "Нарынская область"
                                        7 -> "Иссык-Кульская область"
                                        else -> "Иссык-Кульская область"
                                    }

                                    val sportString = when (i.user.sport) {
                                        1 -> "Дзюдо"
                                        2 -> "Көк бөрү"
                                        3 -> "Борьба"
                                        4 -> "Айкидо"
                                        5 -> "Ушу"
                                        else -> "Дзюдо"
                                    }


                                    fragmentFirstBinding!!.surname.setText(i.user.surname)
                                    fragmentFirstBinding!!.name.setText(i.user.name)
                                    fragmentFirstBinding!!.middleName.setText(i.user.middlename)
                                    fragmentFirstBinding!!.region.setText(regionString)
                                    fragmentFirstBinding!!.phoneNumber.setText(i.user.phone)
                                    fragmentFirstBinding!!.sportType.setText(sportString)

                                }
                            }
                        }
                    }
                    is Resource.Error -> {
                        response.message?.let { message ->
                            Log.d(TAG, "An error occured: $message")
                        }
                    }
                    is Resource.Loading -> {
                        response.message?.let { message ->
                            Log.d(TAG, "An error occured: $message")
                        }
                    }
                }
            })
        }



        val regionArray = resources.getStringArray(R.array.arrayOfRegion)
        val regionAdapter = ArrayAdapter(requireContext(), R.layout.dropdow_item, regionArray)
        fragmentFirstBinding!!.region.setAdapter(regionAdapter)

        fragmentFirstBinding!!.showRegion.setOnClickListener { fragmentFirstBinding!!.region.showDropDown() }

        val sportTypeArray = resources.getStringArray(R.array.arrayOfSportType)
        val sportTypeAdapter = ArrayAdapter(requireContext(), R.layout.dropdow_item, sportTypeArray)
        fragmentFirstBinding!!.sportType.setAdapter(sportTypeAdapter)

        fragmentFirstBinding!!.showSportType.setOnClickListener { fragmentFirstBinding!!.sportType.showDropDown() }



    }

}