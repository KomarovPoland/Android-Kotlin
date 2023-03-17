package com.example.test_sportpro.ui.fragments.news

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_sportpro.R
import com.example.test_sportpro.adapters.SportTypesAdapter
import com.example.test_sportpro.databinding.FragmentSportTypesBinding
import com.example.test_sportpro.repository.SportRepository
import com.example.test_sportpro.ui.SportViewModel
import com.example.test_sportpro.ui.SportViewModelProviderFactory
import com.example.test_sportpro.ui.activities.MainActivity
import com.example.test_sportpro.utils.Resource
import com.example.test_sportpro.utils.SportApplication
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SportTypesFragment : Fragment(R.layout.fragment_sport_types) {

    lateinit var viewModel: SportViewModel
    lateinit var typesAdapter: SportTypesAdapter

    private var fragmentTypesSportBinding: FragmentSportTypesBinding? = null

    private val TAG = "SportTypesFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        fragmentTypesSportBinding = FragmentSportTypesBinding.bind(view)

        val title = arguments?.getString("title")?.replace("\n".toRegex(), " ")
        fragmentTypesSportBinding!!.title.text = title

        fragmentTypesSportBinding!!.backButton.setOnClickListener{ activity?.onBackPressed() }

        val newsRepository = SportRepository()
        val viewModelProviderFactory = SportViewModelProviderFactory(SportApplication(),newsRepository)

        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(SportViewModel::class.java)

        setupRecyclerView()


        when (arguments?.getInt("id")) {
            1 -> viewModel.getSport(1)
            2 -> viewModel.getSport(2)
            3 -> viewModel.getSport(3)
            else -> viewModel.getSport(4)
        }

        typesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putInt("sportId", it.id)
            }
            findNavController().navigate(
                    R.id.action_sportTypesFragment_to_newsFragment,
                    bundle
            )
        }

        viewModel.sport.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { sport ->
                        typesAdapter.differ.submitList(sport)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                }
            }
        })
    }

    private fun setupRecyclerView() {
        typesAdapter = SportTypesAdapter()
        fragmentTypesSportBinding?.rvSportTypes?.apply {
            adapter = typesAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onDestroyView() {
        fragmentTypesSportBinding = null
        super.onDestroyView()
    }
}