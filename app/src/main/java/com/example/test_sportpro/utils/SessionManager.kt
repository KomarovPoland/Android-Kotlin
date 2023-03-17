package com.example.test_sportpro.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.test_sportpro.R

class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_STATUS = "user_status"
        const val USER_PHONE = "user_phone"
        const val TRAINER = "trainer"
        const val TRAINER_SPORT = "trainer_sport"
    }

    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    /**
     * Function to save auth token
     */
    fun saveStatus(status: String) {
        val editor = prefs.edit()
        editor.putString(USER_STATUS, status)
        editor.apply()
    }

    /**
     * Function to save auth token
     */
    fun savePhone(phone: String) {
        val editor = prefs.edit()
        editor.putString(USER_PHONE, phone)
        editor.apply()
    }

    fun saveTrainerInfo(trainer: Int) {
        val editor = prefs.edit()
        editor.putInt(TRAINER, trainer)
        editor.apply()
    }

    fun saveTrainerSportId(trainer: Int) {
        val editor = prefs.edit()
        editor.putInt(TRAINER_SPORT, trainer)
        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    /**
     * Function to fetch auth token
     */
    fun fetchStatus(): String? {
        return prefs.getString(USER_STATUS, null)
    }

    /**
     * Function to fetch auth token
     */
    fun fetchPhone(): String? {
        return prefs.getString(USER_PHONE, null)
    }

    fun fetchTrainerInfo(): Int {
        return prefs.getInt(TRAINER, 0)
    }

    fun fetchTrainerSportId(): Int {
        return prefs.getInt(TRAINER_SPORT, 0)
    }
}