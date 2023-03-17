package com.example.test_sportpro.ui.fragments.judgeProfile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_sportpro.R
import com.example.test_sportpro.adapters.GridsSportsmenAdapter
import com.example.test_sportpro.adapters.GridsStageAdapter
import com.example.test_sportpro.databinding.FragmentCompetitionsGridBinding
import com.example.test_sportpro.ui.SportViewModel
import com.example.test_sportpro.ui.activities.MainActivity
import com.example.test_sportpro.utils.Resource

class CompetitionsGridFragment : Fragment(R.layout.fragment_competitions_grid) {

    val args: CompetitionsGridFragmentArgs by navArgs()

    lateinit var gridsStageAdapter: GridsStageAdapter
    lateinit var gridsSportsmenAdapter: GridsSportsmenAdapter

    lateinit var viewModel: SportViewModel

    var stage: String = "0"

    private val TAG = "GridsFragment"

    private var fragmentCompetitionsGridBinding: FragmentCompetitionsGridBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentCompetitionsGridBinding = FragmentCompetitionsGridBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel

        val competition = args.competition
        val id = args.competition.id
        val name = args.competition.name

        fragmentCompetitionsGridBinding!!.name.text = name

        viewModel.getGrids(id)

        viewModel.grids.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.message?.let { Log.d("TAG_SUCCESS", it) }
                    response.data?.let { grids ->
                        gridsStageAdapter.differ.submitList(grids)
                        grids.forEach() { gridsItem ->
                            if (gridsItem.stage == "1/4")
                                gridsSportsmenAdapter.differ.submitList(gridsItem.matches)
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

        setupRvGrid()
        setupRvSportsmen()

        gridsStageAdapter.setOnItemClickListener {
            setupRvSportsmen()

            gridsSportsmenAdapter.differ.submitList(it.matches)

            gridsSportsmenAdapter.setOnItemClickListener { matche ->
                val bundle = Bundle().apply {
                    putSerializable("matche", matche)
                    putSerializable("competition", competition)
                }
                findNavController().navigate(R.id.action_competitionsGridFragment_to_gridsDetailGridFragment, bundle)
            }
        }
    }

    private fun setupRvGrid() {
        gridsStageAdapter = GridsStageAdapter()
        fragmentCompetitionsGridBinding?.rvGrid?.apply {
            adapter = gridsStageAdapter
            var mmLayoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            mmLayoutManager.stackFromEnd = true
            mmLayoutManager.reverseLayout = true
            layoutManager = mmLayoutManager
        }
    }

    private  fun setupRvSportsmen() {
        gridsSportsmenAdapter = GridsSportsmenAdapter()
        fragmentCompetitionsGridBinding?.rvSportsmen?.apply {
            adapter = gridsSportsmenAdapter
            var mmLayoutManager =
                LinearLayoutManager(requireContext())
//            mmLayoutManager.stackFromEnd = true
//            mmLayoutManager.reverseLayout = true
            layoutManager = mmLayoutManager
        }
    }
}