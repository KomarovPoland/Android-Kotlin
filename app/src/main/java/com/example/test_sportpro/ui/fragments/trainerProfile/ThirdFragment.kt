package com.example.test_sportpro.ui.fragments.trainerProfile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_sportpro.R
import com.example.test_sportpro.adapters.ThirdAdapter
import com.example.test_sportpro.databinding.FragmentThirdBinding
import com.example.test_sportpro.ui.SportViewModel
import com.example.test_sportpro.ui.activities.MainActivity
import com.example.test_sportpro.utils.Resource
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class ThirdFragment : Fragment(R.layout.fragment_third) {

    lateinit var viewModel: SportViewModel
    lateinit var thirdAdapter: ThirdAdapter

    private var fragmentThirdBinding : FragmentThirdBinding? = null

    private val TAG = "ThirdFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentThirdBinding = FragmentThirdBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        MainScope().launch { viewModel.getPlayers() }

        thirdAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("sportsman", it)
            }
            findNavController().navigate(
                    R.id.action_mainProfileFragment_to_thirdInfoFragment,
                    bundle
            )
        }

        fragmentThirdBinding!!.addSportsman.setOnClickListener {
            findNavController().navigate(
                R.id.action_mainProfileFragment_to_thirdInfoFragment
            )
        }

        viewModel.players.observe(viewLifecycleOwner, Observer { response ->


            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.message?.let { Log.d("TAG_SUCCESS", it) }
                    response.data?.let { sportsman ->
                        thirdAdapter.differ.submitList(sportsman)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.d(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                    response.message?.let { message ->
                        Log.d(TAG, "An error occured: $message")
                    }
                }
            }
        })

        fragmentThirdBinding!!.addSportsman.setOnClickListener {
            findNavController().navigate(R.id.action_mainProfileFragment_to_thirdInfoFragment)
        }
    }



    private fun setupRecyclerView() {
        thirdAdapter = ThirdAdapter()
        fragmentThirdBinding?.rvSportsmen?.apply {
            var mmLayoutManager = LinearLayoutManager(requireContext())
            mmLayoutManager.stackFromEnd = true
            mmLayoutManager.reverseLayout = true
            layoutManager = mmLayoutManager
            adapter = thirdAdapter
        }
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }
}