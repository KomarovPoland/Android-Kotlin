package com.GDSC_IUCA.iuca_tour.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.GDSC_IUCA.iuca_tour.api.UserNetwork
import com.GDSC_IUCA.iuca_tour.models.PlacesItem
import kotlinx.coroutines.launch
import retrofit2.Response

class ActivityViewModel: ViewModel() {
    val myResponse1: MutableLiveData<Response<List<PlacesItem>>> = MutableLiveData()

    fun getPost1() {
        viewModelScope.launch {
            myResponse1.value = UserNetwork.retrofit1.getPlaces1()
        }
    }

}