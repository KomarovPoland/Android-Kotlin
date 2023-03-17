package com.example.test_sportpro.ui.fragments.trainerProfile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_sportpro.R
import com.example.test_sportpro.adapters.CompetitionsAdapter
import com.example.test_sportpro.databinding.FragmentComAllBinding

import com.example.test_sportpro.ui.SportViewModel
import com.example.test_sportpro.ui.activities.MainActivity
import com.example.test_sportpro.utils.Resource
import com.example.test_sportpro.utils.SessionManager

class ComAllFragment : Fragment(R.layout.fragment_com_all) {

    lateinit var viewModel: SportViewModel
    lateinit var competitionsAdapter: CompetitionsAdapter
    private var fragmentComAllBinding: FragmentComAllBinding? = null
    private val TAG = "AllEventsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentComAllBinding = FragmentComAllBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        val sessionManager = SessionManager(requireContext())
        val sportId = sessionManager.fetchTrainerSportId()
        
        viewModel.getTrainerEvents(3)
//        viewModel.getTrainerEvents(sportId)

        competitionsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("event", it)
            }
            findNavController().navigate(
                R.id.action_mainProfileFragment_to_detailCompititionFragment,
                    bundle
            )
        }

        viewModel.events.observe(viewLifecycleOwner, Observer { response ->

            when (response) {
                is Resource.Success -> {
                    response.message?.let { Log.d("TAG_SUCCESS", it) }
                    response.data?.let { event ->
                        competitionsAdapter.differ.submitList(event)
                    }
                }
                is Resource.Error -> {
                    response.data
                    response.message?.let { message ->
                        Log.d(TAG, "An error occured: $message")
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

    private fun setupRecyclerView() {
        competitionsAdapter = CompetitionsAdapter()
        fragmentComAllBinding?.rvAllEvents?.apply {
            adapter = competitionsAdapter
            layoutManager = LinearLayoutManager(activity)
        }


    }




}