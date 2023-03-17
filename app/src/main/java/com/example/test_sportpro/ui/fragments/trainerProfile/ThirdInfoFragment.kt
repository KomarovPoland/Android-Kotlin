package com.example.test_sportpro.ui.fragments.trainerProfile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.test_sportpro.R
import com.example.test_sportpro.api.RetrofitInstance
import com.example.test_sportpro.databinding.FragmentThirdInfoBinding
import com.example.test_sportpro.models.*
import com.example.test_sportpro.utils.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThirdInfoFragment : Fragment(R.layout.fragment_third_info) {

    val args: ThirdInfoFragmentArgs by navArgs()

    private var fragmentThirdInfoBinding: FragmentThirdInfoBinding? = null
    private val pickImage = 100
    private var imageUri: Uri? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentThirdInfoBinding = FragmentThirdInfoBinding.bind(view)
        val sessionManager = SessionManager(requireContext())
        fragmentThirdInfoBinding!!.image.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }

        fragmentThirdInfoBinding!!.btnSave.setOnClickListener {
            val contact = fragmentThirdInfoBinding!!.phoneNumber.text.toString().trim()
            val dateofbirth = fragmentThirdInfoBinding!!.birthday.text.toString().trim()
            val middlename = fragmentThirdInfoBinding!!.middleName.text.toString().trim()
            val name = fragmentThirdInfoBinding!!.name.text.toString().trim()
            val photo = fragmentThirdInfoBinding!!.phoneLink.text.toString().trim()
            val playercategory = fragmentThirdInfoBinding!!.playerCategory.text.toString().trim()
            val sex = fragmentThirdInfoBinding!!.gender.text.toString().trim()
            val sport = fragmentThirdInfoBinding!!.sportType.text.toString().trim()
            val surname = fragmentThirdInfoBinding!!.surname.text.toString().trim()
            val weight = fragmentThirdInfoBinding!!.weight.text.toString().trim()
            val licens = fragmentThirdInfoBinding!!.license.text.toString().trim()

            val playerAgeCategory = when (playercategory) {
                "Взрослые" -> 1
                "Для детей" -> 2
                else -> 1
            }
            val sport1 = when (sport) {
                "Дзюдо" -> 1
                "Көк бөрү" -> 2
                "Борьба" -> 3
                "Айкидо" -> 4
                else -> 2
            }

            val trainerId = sessionManager.fetchTrainerInfo()

            RetrofitInstance.api.createNewPlayer(
                PlayersRequest(
                    contact, dateofbirth, middlename, name,
                    photo, playerAgeCategory, sex, sport1, surname, trainerId, weight,licens
                )
            )
                .enqueue(object : Callback<DefaultResponsePlayer> {
                    override fun onResponse(
                        call: Call<DefaultResponsePlayer>,
                        response: Response<DefaultResponsePlayer>
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

                    override fun onFailure(call: Call<DefaultResponsePlayer>, t: Throwable) {
                        Toast.makeText(activity, t.message, Toast.LENGTH_LONG).show()
                        Log.d("TAG_failure", t.message.toString())
                    }
                })

        }

        val genderArray = resources.getStringArray(R.array.arrayOfGender)
        val genderAdapter = ArrayAdapter(requireContext(), R.layout.dropdow_item, genderArray)
        fragmentThirdInfoBinding!!.gender.setAdapter(genderAdapter)

        fragmentThirdInfoBinding!!.showGender.setOnClickListener { fragmentThirdInfoBinding!!.gender.showDropDown() }

//        val regionArray = resources.getStringArray(R.array.arrayOfRegion)
//        val regionAdapter = ArrayAdapter(requireContext(), R.layout.dropdow_item, regionArray)
//        fragmentThirdInfoBinding!!.region.setAdapter(regionAdapter)
//
//        fragmentThirdInfoBinding!!.showRegion.setOnClickListener { fragmentThirdInfoBinding!!.region.showDropDown() }


        val ageCategoryArray = resources.getStringArray(R.array.arrayOfAgeCategory)
        val ageCategoryAdapter = ArrayAdapter(requireContext(), R.layout.dropdow_item, ageCategoryArray)
        fragmentThirdInfoBinding!!.playerCategory.setAdapter(ageCategoryAdapter)

        fragmentThirdInfoBinding!!.showAgeCategory.setOnClickListener { fragmentThirdInfoBinding!!.playerCategory.showDropDown() }


        val sportTypeArray = resources.getStringArray(R.array.arrayOfSportType)
        val sportTypeAdapter = ArrayAdapter(requireContext(), R.layout.dropdow_item, sportTypeArray)
        fragmentThirdInfoBinding!!.sportType.setAdapter(sportTypeAdapter)

        fragmentThirdInfoBinding!!.showSportType.setOnClickListener { fragmentThirdInfoBinding!!.sportType.showDropDown() }

        val sportsman = args.sportsman
        if (sportsman != null) {
            fragmentThirdInfoBinding!!.btnSave.visibility = View.INVISIBLE
        }

        if (sportsman != null) {
            Glide.with(this)
                .load(sportsman.photo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(fragmentThirdInfoBinding!!.image)
        }

        if (sportsman != null) {
            fragmentThirdInfoBinding!!.surname.setText(sportsman.surname)
        }
        if (sportsman != null) {
            fragmentThirdInfoBinding!!.name.setText(sportsman.name)
        }
        if (sportsman != null) {
            fragmentThirdInfoBinding!!.phoneLink.visibility = View.INVISIBLE
        }

        if (sportsman != null) {
            fragmentThirdInfoBinding!!.gender.setText(sportsman.sex)
        }
        if (sportsman != null) {
            fragmentThirdInfoBinding!!.middleName.setText(sportsman.middlename)
        }
        if (sportsman != null) {
            fragmentThirdInfoBinding!!.birthday.setText(sportsman.dateofbirth)
        }
        if (sportsman != null) {
            fragmentThirdInfoBinding!!.weight.setText(sportsman.weight.toString().plus(" кг"))
        }
        if (sportsman != null) {
            fragmentThirdInfoBinding!!.phoneNumber.setText(sportsman.contact.toString())
        }
        val playerAgeCategory1 = when (sportsman?.playercategory) {
            1 -> "Взрослые"
            2 -> "Для детей"
            else -> "Взрослые"
        }
        if (sportsman != null) {
            fragmentThirdInfoBinding!!.playerCategory.setText(playerAgeCategory1)
        }

        val sportStr = when (sportsman?.sport) {
            1 -> "Дзюдо"
            2 -> "Көк бөрү"
            3 -> "Борьба"
            4 -> "Айкидо"
            5 -> "Ушу"
            else -> "Дзюдо"
        }

        if (sportsman != null) {
            fragmentThirdInfoBinding!!.sportType.setText(sportStr)
        }

        if (sportsman != null) {
            fragmentThirdInfoBinding!!.license.setText(sportsman.license.toString())
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            fragmentThirdInfoBinding!!.image.setImageURI(imageUri)
        }
    }
}