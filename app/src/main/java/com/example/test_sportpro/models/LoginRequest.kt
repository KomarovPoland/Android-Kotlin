package com.example.test_sportpro.models

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName
import retrofit2.Call

data class LoginRequest(
        @SerializedName("user")
        var user: UserPhone
)
