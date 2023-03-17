package com.example.test_sportpro.models

data class Sport(
    val category: Category,
    val description: String,
    val id: Int,
    val name: String,
    val photo: String,
    val short_desc: String
)