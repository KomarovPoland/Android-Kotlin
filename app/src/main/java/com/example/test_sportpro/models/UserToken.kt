package com.example.test_sportpro.models

import com.google.gson.annotations.SerializedName

data class UserToken(
        @SerializedName("token")
        val token: String
)
