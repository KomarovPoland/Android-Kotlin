package com.example.test_sportpro.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ArticleItem(
    @SerializedName("article")
    val article: String,
    @SerializedName("author")
    val author: Author,
    @SerializedName("dateofadd")
    val dateofadd: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("sport")
    val sport: Sport,
    @SerializedName("tags")
    val tags: String,
    @SerializedName("title")
    val title: String
) : Serializable