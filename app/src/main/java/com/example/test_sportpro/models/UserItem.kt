package com.example.test_sportpro.models
import java.io.Serializable
data class UserItem(
    val document: String,
    val id: Int,
    val middlename: String,
    val name: String,
    val organization: String,
    val phone: String,
    val region: Int,
    val role: Int,
    val sport: Int,
    val surname: String,
    val photo: String
) : Serializable