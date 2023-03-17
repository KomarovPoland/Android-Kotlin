package com.example.test_sportpro.ui.fragments.trainerProfile

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.test_sportpro.R
import com.example.test_sportpro.adapters.ViewPagerAdapter
import com.example.test_sportpro.databinding.FragmentMainFragmentBinding
import com.example.test_sportpro.models.UserItem
import com.example.test_sportpro.ui.SportViewModel
import com.example.test_sportpro.ui.activities.MainActivity
import com.example.test_sportpro.utils.Resource
import com.example.test_sportpro.utils.SessionManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainProfileFragment : Fragment(R.layout.fragment_main_fragment) {


    private var fragmentMainFragmentBinding: FragmentMainFragmentBinding? = null
    lateinit var viewModel: SportViewModel
    private val TAG = "TAG_MainProfileF"

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentMainFragmentBinding = FragmentMainFragmentBinding.bind(view)

        fragmentMainFragmentBinding!!.sportType.setOnClickListener {
//            findNavController().navigate(R.id.action_mainProfileFragment_to_profileFragment)
            activity?.let{
                val intent = Intent (it, MainActivity::class.java)
                it.startActivity(intent)
            }

        }
        val sessionManager = SessionManager(requireContext())
        var number = sessionManager.fetchPhone()

//        Toast.makeText(activity, number, Toast.LENGTH_LONG).show()

        // This callback will only be called when MyFragment is at least Started.
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    // Handle the back button event
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        // The callback can be enabled or disabled here or in handleOnBackPressed()

        viewModel = (activity as MainActivity).viewModel
        var object1: UserItem

        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        val viewPager2 = view.findViewById<ViewPager2>(R.id.view_pager_2)

        val adapter = activity?.let { ViewPagerAdapter(it.supportFragmentManager, lifecycle) }


        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Данные"
                }
                1 -> {
                    tab.text = "Соревнования"
                }
                2 -> {
                    tab.text = "Спортсмены"
                }
            }
        }.attach()

        if (findNavController().previousBackStackEntry?.arguments?.getSerializable("user") != null) {
            val user = findNavController().previousBackStackEntry?.arguments?.getSerializable("user") as UserItem

            // set data
            val sharedPreference = this.requireActivity().getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.putString("number", number.toString())
            editor.commit()

            //get data
            number = sharedPreference.getString("number", "+996707444345")


            fragmentMainFragmentBinding!!.fullName.text = "${user.surname} ${user.name} ${user.middlename}"

            if (user.photo != null) {
                Glide.with(this)
                    .load(user.photo)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(fragmentMainFragmentBinding!!.imageOfTrainer)
            }

            val sportString = when (user.sport) {
                1 -> "Дзюдо"
                2 -> "Көк бөрү"
                3 -> "Борьба"
                4 -> "Айкидо"
                5 -> "Ушу"
                else -> "Дзюдо"
            }
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

            fragmentMainFragmentBinding!!.sportType.text = "Тренер: $sportString"

            sessionManager.saveTrainerInfo(user.id)
        } else {

            viewModel.getTrainers()

            viewModel.trainers.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is Resource.Success -> {
                        response.message?.let { Log.d("TAG_SUCCESS", it) }

                        response.data?.let { user ->
                            for (i in user) {
                                if (i.user.phone == number) {

//                                Toast.makeText(activity, i.user.name.toString(), Toast.LENGTH_LONG).show()

                                    Log.d("TAG_User", i.user.toString())
                                    object1 = i.user
                                    fragmentMainFragmentBinding!!.fullName.text = "${i.user.surname} ${i.user.name} ${i.user.middlename}"

                                    if (i.user.photo != null) {
                                        Glide.with(this)
                                            .load(i.user.photo)
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .into(fragmentMainFragmentBinding!!.imageOfTrainer)
                                    }

                                    val sportString = when (i.user.sport) {
                                        1 -> "Дзюдо"
                                        2 -> "Көк бөрү"
                                        3 -> "Борьба"
                                        4 -> "Айкидо"
                                        5 -> "Ушу"
                                        else -> "Дзюдо"
                                    }
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


                                    fragmentMainFragmentBinding!!.sportType.text =
                                        "Тренер: $sportString"

                                    sessionManager.saveTrainerInfo(i.user.id)

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
            }

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