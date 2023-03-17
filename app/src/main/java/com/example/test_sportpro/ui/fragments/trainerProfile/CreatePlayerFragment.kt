package com.example.test_sportpro.ui.fragments.trainerProfile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import com.example.test_sportpro.R
import com.example.test_sportpro.api.RetrofitInstance
import com.example.test_sportpro.databinding.FragmentCreatePlayerBinding
import com.example.test_sportpro.models.DefaultResponse
import com.example.test_sportpro.models.DefaultResponsePlayer
import retrofit2.Call
import retrofit2.Response

class CreatePlayerFragment : Fragment(R.layout.fragment_create_player) {

    private var fragmentCreatePlayerBinding: FragmentCreatePlayerBinding? = null
    private val TAG = "AllEventsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentCreatePlayerBinding = FragmentCreatePlayerBinding.bind(view)

        fragmentCreatePlayerBinding!!.btnSavePlayer.setOnClickListener {

            val surname = fragmentCreatePlayerBinding!!.tvSurname.text.toString().trim()
            val name = fragmentCreatePlayerBinding!!.tvName.text.toString().trim()
            val middleName = fragmentCreatePlayerBinding!!.tvMiddleName.text.toString().trim()
            val birthday = fragmentCreatePlayerBinding!!.tvBirthday.text.toString().trim()
            val gender = fragmentCreatePlayerBinding!!.acTvGender.text.toString().trim()
            val number = fragmentCreatePlayerBinding!!.tvPhoneNumber.text.toString().trim()
            val weight = fragmentCreatePlayerBinding!!.tvWeight.text.toString().trim()
            val typeSport = fragmentCreatePlayerBinding!!.acTvSportType.text.toString().trim()

            if (surname.isEmpty()) {
                fragmentCreatePlayerBinding!!.tvSurname.error = "это поле обязательное"
                fragmentCreatePlayerBinding!!.tvSurname.requestFocus()
                return@setOnClickListener
            }
            if (name.isEmpty()) {
                fragmentCreatePlayerBinding!!.tvName.error = "это поле обязательное"
                fragmentCreatePlayerBinding!!.tvName.requestFocus()
                return@setOnClickListener
            }
            if (middleName.isEmpty()) {
                fragmentCreatePlayerBinding!!.tvMiddleName.error = "это поле обязательное"
                fragmentCreatePlayerBinding!!.tvMiddleName.requestFocus()
                return@setOnClickListener
            }
            if (birthday.isEmpty()) {
                fragmentCreatePlayerBinding!!.tvBirthday.error = "это поле обязательное"
                fragmentCreatePlayerBinding!!.tvBirthday.requestFocus()
                return@setOnClickListener
            }
            if (gender.isEmpty()) {
                fragmentCreatePlayerBinding!!.acTvGender.error = "это поле обязательное"
                fragmentCreatePlayerBinding!!.acTvGender.requestFocus()
                return@setOnClickListener
            }
            if (number.isEmpty()) {
                fragmentCreatePlayerBinding!!.tvPhoneNumber.error = "это поле обязательное"
                fragmentCreatePlayerBinding!!.tvPhoneNumber.requestFocus()
                return@setOnClickListener
            }
            if (weight.isEmpty()) {
                fragmentCreatePlayerBinding!!.tvWeight.error = "это поле обязательное"
                fragmentCreatePlayerBinding!!.tvWeight.requestFocus()
                return@setOnClickListener
            }
            if (typeSport.isEmpty()) {
                fragmentCreatePlayerBinding!!.acTvSportType.error = "это поле обязательное"
                fragmentCreatePlayerBinding!!.acTvSportType.requestFocus()
                return@setOnClickListener
            }

//            RetrofitInstance.api.createNewPlayer(
//                name,
//                surname,
//                middleName,
//
//            ).enqueue(object : retrofit2.Callback<DefaultResponsePlayer> {
//                override fun onResponse(
//                    call: Call<DefaultResponsePlayer>,
//                    response: Response<DefaultResponsePlayer>
//                ) {
//                    if (response.isSuccessful) {
//                        Log.d("TAG_SUCCESS" + response.body(), response.message())
//                        Log.d("TAG_SUCCESS", response.message())
////                        findNavController().navigate(R.id.action_registerFragment_to_mainProfileFragment)
//
//                    } else {
//                        Log.d("TAG_ERROR_MESSAGE", response.message())
//                        Log.d("TAG_ERROR_BODY", response.body().toString())
//                        Log.d("TAG_ERROR_ERRORBODY", response.errorBody().toString())
//                        Log.d("TAG_ERROR_CODE", response.code().toString())
//                        response.body()?.let { it1 -> Log.d("TAG_ERROR_CODE", it1.name) }
//                    }
//
//                    Toast.makeText(activity, response.message(), Toast.LENGTH_LONG).show()
//
//                }
//
//                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
//                    Toast.makeText(activity, t.message, Toast.LENGTH_LONG).show()
//                    Log.d("tag_failure", t.message.toString())
//                }
//            })
//
        }


    }


}