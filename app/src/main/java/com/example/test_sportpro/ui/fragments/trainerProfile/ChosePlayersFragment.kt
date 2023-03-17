package com.example.test_sportpro.ui.fragments.trainerProfile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_sportpro.R
import com.example.test_sportpro.api.RetrofitInstance
import com.example.test_sportpro.databinding.FragmentChosePlayersBinding
import com.example.test_sportpro.models.DefaultResponse
import com.example.test_sportpro.models.PlayerList
import com.example.test_sportpro.ui.SportViewModel
import com.example.test_sportpro.ui.activities.MainActivity
import com.example.test_sportpro.utils.Resource
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChosePlayersFragment : Fragment(R.layout.fragment_chose_players) {

    lateinit var viewModel: SportViewModel
    lateinit var chosePlayerAdapter: ChosePlayerAdapter

    private var fragmentChosePlayersBinding: FragmentChosePlayersBinding? = null

    private val TAG = "ThirdFragment"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentChosePlayersBinding = FragmentChosePlayersBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        MainScope().launch { viewModel.getPlayers() }

        fragmentChosePlayersBinding!!.sibmitList.setOnClickListener {

            Log.e("DIFF", chosePlayerAdapter.getList().toString())

            RetrofitInstance.api.createPlayerList(

                chosePlayerAdapter.getList(),
                7

            ).enqueue(object : Callback<PlayerList> {
                override fun onResponse(
                    call: Call<PlayerList>,
                    response: Response<PlayerList>
                ) {
                    if (response.isSuccessful) {
                        Log.d("TAG_SUCCESS" + response.body(), response.message())
                        Log.d("TAG_SUCCESS", response.message())
                    } else {
                        Log.d("TAG_ERROR_MESSAGE", response.message())
                        Log.d("TAG_ERROR_BODY", response.body().toString())
                        Log.d("TAG_ERROR_ERRORBODY", response.errorBody().toString())
                        Log.d("TAG_ERROR_CODE", response.code().toString())
                    }
                    Toast.makeText(activity, response.message(), Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<PlayerList>, t: Throwable) {
                    Toast.makeText(activity, t.message, Toast.LENGTH_LONG).show()
                    Log.d("tag_failure", t.message.toString())
                }
            })


            findNavController().navigate(R.id.action_chosePlayersFragment_to_playersConfirmationFragment)
        }

        viewModel.players.observe(viewLifecycleOwner, Observer { response ->

            when (response) {
                is Resource.Success -> {
//                    hideProgressBar()
                    response.message?.let { Log.d("TAG_SUCCESS", it) }
                    response.data?.let { sportsman ->
                        chosePlayerAdapter.differ.submitList(sportsman)
                    }
                }
                is Resource.Error -> {
//                    hideProgressBar()
                    response.message?.let { message ->
                        Log.d(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
//                    showProgressBar()
                    response.message?.let { message ->
                        Log.d(TAG, "An error occured: $message")
                    }
                }
            }
        })
    }

    private fun setupRecyclerView() {
        chosePlayerAdapter = ChosePlayerAdapter()
        fragmentChosePlayersBinding?.rvPlayers?.apply {
            var mmLayoutManager = LinearLayoutManager(requireContext())
            mmLayoutManager.stackFromEnd = true
            mmLayoutManager.reverseLayout = true
            layoutManager = mmLayoutManager
            adapter = chosePlayerAdapter
        }
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }
}