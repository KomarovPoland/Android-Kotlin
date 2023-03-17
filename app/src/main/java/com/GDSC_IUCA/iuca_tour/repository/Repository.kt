package com.GDSC_IUCA.iuca_tour.repository

import com.GDSC_IUCA.iuca_tour.api.RetrofitInstance
import com.GDSC_IUCA.iuca_tour.models.PlacesItem
import com.GDSC_IUCA.iuca_tour.models.PresetItem
import retrofit2.Response

class Repository {
    suspend fun getPlaces(lan: String): Response<List<PlacesItem>> {
        return  RetrofitInstance.api.getPlaces(lan)

    }

    suspend fun getPresent(tourId: Int): Response<PresetItem>{
        return RetrofitInstance.api.getPresetId(tourId)
    }

    suspend fun getItemPlace(number: Int): Response<PlacesItem>{
        return RetrofitInstance.api.getItemPlace(number)
    }
}