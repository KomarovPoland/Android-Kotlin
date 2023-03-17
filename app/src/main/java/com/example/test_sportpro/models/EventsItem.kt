package com.example.test_sportpro.models
import java.io.Serializable
data class EventsItem(
    val creator: Creator,
    val dateofend: String,
    val dateofstart: String,
    val description: String,
    val judge: Int,
    val id: Int,
    val location: String,
    val name: String,
    val photo: String,
    val players: List<Any>,
    val protocol: String,
    val sport: Sport,
    val startofWeighing: String,
    val status: String
) : Serializable