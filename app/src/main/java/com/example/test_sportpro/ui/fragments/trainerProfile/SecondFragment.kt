package com.example.test_sportpro.ui.fragments.trainerProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.test_sportpro.R
import com.example.test_sportpro.adapters.ViewPageAdapterCompetitions
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class SecondFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout_second)
        val viewPager2 = view.findViewById<ViewPager2>(R.id.view_pager_second)


        val adapter = activity?.let { ViewPageAdapterCompetitions(it.supportFragmentManager, lifecycle) }


        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Все"
                }
                1 -> {
                    tab.text = "Мои"
                }

            }
        }.attach()


    }


}