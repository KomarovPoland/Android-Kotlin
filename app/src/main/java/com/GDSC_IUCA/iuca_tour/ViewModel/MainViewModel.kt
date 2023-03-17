package com.GDSC_IUCA.iuca_tour.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.GDSC_IUCA.iuca_tour.models.PlacesItem
import com.GDSC_IUCA.iuca_tour.models.PresetItem
import com.GDSC_IUCA.iuca_tour.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {
    val myResponse: MutableLiveData<Response<List<PlacesItem>>> = MutableLiveData()
    val myResponseItem: MutableLiveData<Response<PlacesItem>> = MutableLiveData()
    val myResponsePreset: MutableLiveData<Response<PresetItem>> = MutableLiveData()


    fun getPlace(lan: String) {
        viewModelScope.launch {
            val response = repository.getPlaces(lan)
            myResponse.value = response
        }
    }


    fun getItemPlace(number: Int) {
        viewModelScope.launch {
            val response = repository.getItemPlace(number)
            myResponseItem.value = response

        }
    }


    fun getPreset(tourId: Int) {
        viewModelScope.launch {
            val response = repository.getPresent(tourId)
            myResponsePreset.value = response
        }
    }


}