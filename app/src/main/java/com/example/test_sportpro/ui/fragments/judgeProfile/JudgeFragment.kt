package com.example.test_sportpro.ui.fragments.judgeProfile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_sportpro.R
import com.example.test_sportpro.adapters.CompetitionsAdapter
import com.example.test_sportpro.databinding.FragmentJudgeBinding
import com.example.test_sportpro.models.UserItem
import com.example.test_sportpro.ui.SportViewModel
import com.example.test_sportpro.ui.activities.MainActivity
import com.example.test_sportpro.utils.Resource
import com.example.test_sportpro.utils.SessionManager


class JudgeFragment : Fragment(R.layout.fragment_judge) {

    lateinit var viewModel: SportViewModel
    lateinit var competitionAdapter: CompetitionsAdapter

    private var fragmentJudgeBinding: FragmentJudgeBinding? = null

    private val TAG = "JudgeFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentJudgeBinding = FragmentJudgeBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        viewModel.getAllSport()


        fragmentJudgeBinding!!.sportType.setOnClickListener {
            findNavController().navigate(R.id.action_judgeFragment_to_profileFragment)

        }

        val sessionManager = SessionManager(requireContext())
        val number = sessionManager.fetchPhone().toString()

        // This callback will only be called when MyFragment is at least Started.
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    // Handle the back button event
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        // The callback can be enabled or disabled here or in handleOnBackPressed()

        if (findNavController().previousBackStackEntry?.arguments?.getSerializable("user") != null) {
            val user = findNavController().previousBackStackEntry?.arguments?.getSerializable("user") as UserItem

            fragmentJudgeBinding!!.name.text =
                user.surname.plus(" ").plus(user.name).plus(" ").plus(user.middlename)

            viewModel.sport.observe(viewLifecycleOwner, Observer { response ->
                when (response) {
                    is Resource.Success -> {
                        hideProgressBar()
                        response.message?.let { Log.d("TAG_SUCCESS", it) }
                        response.data?.let { sportArray ->
                            sportArray.forEach() { sport ->
                                if (sport.id == user.sport) {
                                    fragmentJudgeBinding!!.sportCategory.text = sport.category.name
                                    fragmentJudgeBinding!!.sportType.text = sport.name
                                }
                            }
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
            viewModel.getEvents(user.sport, user.id)

        } else {
            viewModel.getUsers()

            viewModel.users.observe(viewLifecycleOwner, Observer { response ->
                when (response) {
                    is Resource.Success -> {
                        hideProgressBar()
                        response.message?.let { Log.d("TAG_SUCCESS", it) }
                        response.data?.let { userList ->
                            for (userItem in userList)
                                if (userItem.phone == number) {
                                    fragmentJudgeBinding!!.name.text = userItem.surname.plus(" ").plus(userItem.name).plus(" ").plus(
                                        userItem.middlename
                                    )

                                    viewModel.sport.observe(viewLifecycleOwner, Observer { response ->
                                        when (response) {
                                            is Resource.Success -> {
                                                hideProgressBar()
                                                response.message?.let { Log.d("TAG_SUCCESS", it) }
                                                response.data?.let { sportArray ->
                                                    sportArray.forEach() { sport ->
                                                        if (sport.id == userItem.sport) {
                                                            fragmentJudgeBinding!!.sportCategory.text = sport.category.name
                                                            fragmentJudgeBinding!!.sportType.text = sport.name

                                                            viewModel.getEvents(userItem.sport, userItem.id)
                                                        }
                                                    }
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
                                }
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
        }

        competitionAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("competition", it)
            }
            findNavController().navigate(
                R.id.action_judgeFragment_to_judgeCompetitionFragment,
                bundle
            )
        }

        viewModel.events.observe(viewLifecycleOwner, Observer { response ->

            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.message?.let { Log.d("TAG_SUCCESS", it) }
                    response.data?.let { competition ->
                        competitionAdapter.differ.submitList(competition)
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
    }

    private fun setupRecyclerView() {
        competitionAdapter = CompetitionsAdapter()
        fragmentJudgeBinding?.rv?.apply {
            adapter = competitionAdapter
            var mmLayoutManager =
                LinearLayoutManager(requireContext())
            mmLayoutManager.stackFromEnd = true
            mmLayoutManager.reverseLayout = true
            layoutManager = mmLayoutManager
        }
    }

    private fun hideProgressBar() {
        fragmentJudgeBinding?.progressBar?.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        fragmentJudgeBinding?.progressBar?.visibility = View.VISIBLE
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