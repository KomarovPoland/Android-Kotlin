package com.example.test_sportpro.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.test_sportpro.repository.SportRepository

class SportViewModelProviderFactory(
    val app: Application,
    val sportRepository: SportRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SportViewModel(app, sportRepository) as T
    }
}