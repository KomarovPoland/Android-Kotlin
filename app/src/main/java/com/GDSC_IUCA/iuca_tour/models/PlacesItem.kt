package com.GDSC_IUCA.iuca_tour.models

data class PlacesItem(
    val audio: String,
    val desc: String,
    val id: Int,
    val images: List<String>,
    val lang: String,
    val name: String,
    val onMap: String,
    val unifiedName: String
)