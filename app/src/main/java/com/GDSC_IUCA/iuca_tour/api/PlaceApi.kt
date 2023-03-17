package com.GDSC_IUCA.iuca_tour.api

import com.GDSC_IUCA.iuca_tour.models.PlacesItem
import com.GDSC_IUCA.iuca_tour.models.PresetItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlaceApi {
    @GET("api/place/")
    suspend fun getPlaces(
        @Query("lang")
        language: String

    ): Response<List<PlacesItem>>

    @GET("api/place/")
    suspend fun getPlaces1(
        @Query("lang")
        language: String = "ENG"

    ): Response<List<PlacesItem>>



    @GET("api/preset/{tourId}") // long preset
    suspend fun getPresetId(
        @Path("tourId") tourId: Int,
    ): Response<PresetItem>

@GET("api/place/{id}")
    suspend fun getItemPlace(
        @Path("id") number: Int,
    @Query("lang")
    language: String = "ENG"

    ): Response<PlacesItem>
}