package com.example.test_sportpro.models

data class DefaultResponsePlayer(
    val id: Int,
    val name: String,
    val surname: String,
    val age: String,
    val sport: Sport,
    val trainer: UserItem,
    val organization: String,
    val sex: String,
    val weight: Int,
    val playercategory: Int,
    val photo: String,
    val dateofadd: String,
    val score: Int

)

