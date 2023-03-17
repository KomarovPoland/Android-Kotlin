package com.example.test_sportpro.ui.fragments.judgeProfile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_sportpro.R
import com.example.test_sportpro.adapters.AgeCategoriesAdapter
import com.example.test_sportpro.databinding.FragmentCategoriesBinding
import com.example.test_sportpro.models.EventsItem

class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    lateinit var categoriesAdapter: AgeCategoriesAdapter

    private var fragmentCategoriesBinding : FragmentCategoriesBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentCategoriesBinding = FragmentCategoriesBinding.bind(view)

        setupRecyclerView()

//        val competition = findNavController().previousBackStackEntry?.arguments?.getSerializable("competition") as EventsItem

        categoriesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("category", it)
//                putSerializable("competition", competition)
            }
            findNavController().navigate(
                    R.id.action_categoriesFragment_to_chosePlayersFragment,
                    bundle
            )
        }
    }

    private fun setupRecyclerView() {
        val categories : ArrayList<String> = ArrayList()
        categories.add("Взрослые 1976-2002")
        categories.add("Юниоры 2003-2005")
        categories.add("Кадеты 2006-2008")
        categories.add("Дети 2009-2010")
        categories.add("Дети 8-9 лет 2011-2012")
        categories.add("Дети 6-7 лет 2013-2014")


        categoriesAdapter = AgeCategoriesAdapter(categories)
        fragmentCategoriesBinding?.rv?.apply {
            adapter = categoriesAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}