package com.GDSC_IUCA.iuca_tour

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.GDSC_IUCA.iuca_tour.ViewModel.MainViewModel
import com.GDSC_IUCA.iuca_tour.ViewModel.MainViewModelFactory
import com.GDSC_IUCA.iuca_tour.databinding.FragmentStartExcurtionBinding
import com.GDSC_IUCA.iuca_tour.models.PlacesItem
import com.GDSC_IUCA.iuca_tour.models.PresetItem
import com.GDSC_IUCA.iuca_tour.repository.Repository
import com.GDSC_IUCA.iuca_tour.ui.MainPageActivity
import com.denzcoskun.imageslider.models.SlideModel
import com.kaopiz.kprogresshud.KProgressHUD
import retrofit2.Response
import kotlin.properties.Delegates


class StartExcurtionFragment : Fragment(R.layout.fragment_start_excurtion) {
    private val args: StartExcurtionFragmentArgs by navArgs()

    //Retrofit
    private lateinit var viewModel: MainViewModel
    private lateinit var viewModel1: MainViewModel
    private val presetMap: MutableMap<Int, Int> = mutableMapOf()
    private val orderIdOfPlaces = ArrayList<String>()
    private var placesSize by Delegates.notNull<Int>()
    private lateinit var binding: FragmentStartExcurtionBinding

    private lateinit var list: ArrayList<String>
    private lateinit var listOfPlaceNames: ArrayList<String>
    private var listOfPlaceImages = ArrayList<PlacesItem>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStartExcurtionBinding.bind(view)







            val hud = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setBackgroundColor(Color.parseColor("#6F7E7D7D"))
                .show()

            // Retrofit code
            val repository = Repository()
            val viewModelFactory = MainViewModelFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
            Log.d("TOURDURATION", args.tourDuration.toString())
            Log.d("LANGI",args.lng.toString())

            viewModel.getPreset(args.tourDuration.toInt())
            Log.d("viewMode",viewModel.toString())

            viewModel.myResponsePreset.observe(viewLifecycleOwner, Observer { response ->
                Log.d("RESPONSE", response.body().toString())

                //  progressDoalog.dismiss();

                if (response.isSuccessful) {
                    hud.dismiss()

                    //  progressDoalog.dismiss();

                    list = convertMupToList(response)
                    Log.d("PLACE LIST", list.toString())

                    listOfPlaceNames = ArrayList()
                    listOfPlaceImages = ArrayList()
                    val repository1 = Repository()
                    val viewModelFactory1 = MainViewModelFactory(repository1)
                    viewModel1 =
                        ViewModelProvider(this, viewModelFactory1).get(MainViewModel::class.java)

                    // Shared preference
                    val sharedPre = this.activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)

                    val str = sharedPre?.getString("setOrderedPlaces", null)
                    if (str != null) {
                        Log.d("Set oder places", str)
                    } else {
                        Log.d("Set order", 1.toString())
                    }
                    val chars: List<Char> = str!!.toList()
                    Log.d("LIST",chars.toString())




                    val lang = sharedPre.getString("lang", null)
                    Log.d("LANGI",lang.toString())
                    viewModel1.getPlace(lang!!)
                    viewModel1.myResponse.observe(viewLifecycleOwner, Observer { res ->
                        Log.d("RESPONSE", res.body().toString())
                        if (res.isSuccessful) {
                            chars.forEach {
                                listOfPlaceNames.add(res.body()!![it.digitToInt() - 1].name)
                                listOfPlaceImages.add(res.body()!![it.digitToInt() - 1])
                            }
                        } else Log.d("RESPONSE ERROR", res.errorBody().toString())


                        // Simple adapter
                        val data = (0 until listOfPlaceNames.size).map {

                            Log.d("LIST SIZE", listOfPlaceNames[it])

                            mapOf(
                                KEY_TITLE to "${it + 1}. ${listOfPlaceNames.get(it)}"
                            )
                        }
                        val adapter = SimpleAdapter(
                            context,
                            data,
                            R.layout.item_custem,
                            arrayOf(KEY_TITLE),
                            intArrayOf(R.id.tv_title)
                        )
                        binding.listView.adapter = adapter
                        // Slider code
                        var imageList = ArrayList<SlideModel>() // Create image list

                        listOfPlaceImages.forEach {
                            //    < - - - - SLIDER IMAGES- - - - >

                            val t = it.images.get(0)
                            Log.d("IMAGE", t.toString())

                            val t2 = "http://tour.iuca.kg$t"
                            Log.d("IMAGES", t2)

                            imageList.add(SlideModel(t2))
                        }

                        val imageS = binding.imageSlider
                        imageS.setImageList(imageList)

                        // ========   ======
                        val photoUrls = arrayOf(
                            "http://example/photo.jpg",
                            "http://example2.jpeg"
                        )


//                    val viewPager = binding.imageSlider as ViewPager?

//                    if (viewPager != null) {
//                        viewPager.adapter = CustomImageSliderAdapter(context, photoUrls)
//                    }
                        // =======   =======

                    })

                } else{
                    Log.d("RESPONSE ERROR", response.errorBody().toString())
                    hud.dismiss()

                }
            })



            // Navigation code
            binding.button3.setOnClickListener {
                val intent = Intent(activity, MainPageActivity::class.java)
                activity?.startActivity(intent)
            }


    }



    private fun convertMupToList(response: Response<PresetItem>): ArrayList<String> {

        placesSize = response.body()!!.places.size
        // put places to Map
        for (i in 0 until placesSize) {
            presetMap.put(response.body()!!.places[i].place, response.body()!!.places[i].order)
//            presetMap.put(response.body()!!.places[i].order,response.body()!!.places[i].place)
            Log.d("RESPONSE  1:", response.body()!!.places[i].place.toString()+"   2:"+response.body()!!.places[i].order.toString())

        }

        Log.d("RESPONSE", response.body().toString())
        Log.d("RESPONSE_SIZE", response.body()!!.places.size.toString())

        for (i in 1 until response.body()!!.places.size + 1) {
            orderIdOfPlaces.add(presetMap.getValue(i+3).toString()) // Azamat
        }

        saveListSharePref(orderIdOfPlaces)
        return orderIdOfPlaces
    }


    private fun saveListSharePref(orderIdOfPlaces: ArrayList<String>) {
        val orderIdOfPlacesStr = orderIdOfPlaces.joinToString("")

        val sharedPre = this.activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = sharedPre?.edit()

        editor?.apply {
            putString("setOrderedPlaces", orderIdOfPlacesStr)
            putInt("counter", 0)
        }?.apply()
    }


    companion object {
        @JvmStatic
        val KEY_TITLE = "title"
    }


}