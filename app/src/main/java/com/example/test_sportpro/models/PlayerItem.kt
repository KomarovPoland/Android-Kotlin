package com.example.test_sportpro.models
import java.io.Serializable

data class PlayerItem(
    val age: Int,
    val contact: String,
    val dateofadd: String,
    val dateofbirth: String,
    val id: Int,
    val middlename: String,
    val name: String,
    val organization: String,
    val photo: String,
    val playercategory: Int,
    val score: Int,
    val sex: String,
    val sport: Int,
    val surname: String,
    val trainer: Int,
    val weight: Int,
    val license: String,
    val phoneNumber: String
): Serializable