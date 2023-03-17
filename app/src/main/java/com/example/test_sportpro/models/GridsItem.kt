package com.example.test_sportpro.models


import com.google.gson.annotations.SerializedName

data class GridsItem(
    @SerializedName("event")
    val event: Int,
    @SerializedName("matches")
    val matches: List<Matche>,
    @SerializedName("stage")
    val stage: String
)