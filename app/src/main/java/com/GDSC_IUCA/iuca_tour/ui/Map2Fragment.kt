package com.GDSC_IUCA.iuca_tour.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.GDSC_IUCA.iuca_tour.R
import com.GDSC_IUCA.iuca_tour.ViewModel.MainViewModel
import com.GDSC_IUCA.iuca_tour.ViewModel.MainViewModelFactory
import com.GDSC_IUCA.iuca_tour.databinding.FragmentMap2Binding
import com.GDSC_IUCA.iuca_tour.repository.Repository
import com.bumptech.glide.Glide


class Map2Fragment : Fragment() {
    private lateinit var binding: FragmentMap2Binding
    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map2, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMap2Binding.bind(view)

        saveCountData()

        // Shared preference
        val sharedPre = this.activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)


        val editor = sharedPre?.edit()
        editor?.apply {
            putInt("backStatus", 1)
        }?.apply()
        Log.e("Map2Fragment", "Установили: backStatus = 1")

        val counter = sharedPre?.getInt("counter", 0)
        val setOrderedPlaces = sharedPre?.getString("setOrderedPlaces", null)
        Log.e("SET_ORDER_PLACES", setOrderedPlaces.toString())

        val listOrderedPlaces: List<Char> = setOrderedPlaces!!.toList() // ordered list of places

        val idOfCurrPlaceChar = listOrderedPlaces.elementAt(counter!!.toInt())
        var idCurrPlace = idOfCurrPlaceChar.digitToInt() // id of current place

        var lastElement = listOrderedPlaces.last().digitToInt() // green color
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        val lang = sharedPre.getString("lang", null)


        binding.nextStationBtn.setOnClickListener {


            Navigation.findNavController(view)
                .navigate(R.id.action_testFragment_to_mainPageFragment)

            val editor = sharedPre?.edit()
            editor?.apply {
                putInt("backStatus", 2)
            }?.apply()
            Log.e("Map2Fragment", "Установили: backStatus = 2")


        }


        viewModel.getItemPlace(idCurrPlace+3) ///Azamat hard code
        viewModel.getPlace(lang!!)

        viewModel.myResponseItem.observe(viewLifecycleOwner, Observer { response ->
            viewModel.myResponse.observe(viewLifecycleOwner, Observer { res ->
                val namesOrderedPlaces: ArrayList<String> = ArrayList()

                listOrderedPlaces.forEach {
                    namesOrderedPlaces.add(res.body()!![it.digitToInt() - 1].name)

                    Log.d("NAMES", namesOrderedPlaces.toString())
                }

            })

            if (response.isSuccessful) {
                (requireActivity() as MainPageActivity).title = response.body()?.name!!.uppercase()


                binding.tvNextPlace.text = response.body()!!.name.toString()
                val it = response.body()!!.images[0]
                val t = it.replace("[]", "")
                val t2 = "http://tour.iuca.kg$t"

                Glide.with(requireContext())
                    .load(t2)
                    .circleCrop()
                    .placeholder(R.drawable.loading_place_holder)
                    .into(binding.imageViewFuture)


            } else {
                Log.d("response", response.errorBody().toString())
                Log.d("response", response.message())
                Log.d("response", response.body().toString())

            }
        })
    }

    private fun saveCountData() {

        val sharedPre = this.activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = sharedPre?.edit()
        var counter = sharedPre?.getInt("counter", 0)

        Log.d("Counter", counter.toString())

        counter = counter!!.toInt() + 1

        Log.d("Counter2", counter.toString())

        editor?.apply {
            putInt("counter", counter)
        }?.apply()
    }
}


