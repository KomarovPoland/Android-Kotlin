package com.example.test_sportpro.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Matche(
    @SerializedName("date")
    val date: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("judge")
    val judge: Judge,
    @SerializedName("player1")
    val player1: Player1,
    @SerializedName("player1_score")
    val player1Score: Int,
    @SerializedName("player2")
    val player2: Player2,
    @SerializedName("player2_score")
    val player2Score: Int,
    @SerializedName("winner")
    val winner: Winner
) : Serializable