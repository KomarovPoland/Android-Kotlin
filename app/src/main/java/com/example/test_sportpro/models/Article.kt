package com.example.test_sportpro.models


import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: MutableList<ArticleItem>
)