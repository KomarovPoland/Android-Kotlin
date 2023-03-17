package com.example.test_sportpro.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_sportpro.models.*
import com.example.test_sportpro.repository.SportRepository
import com.example.test_sportpro.utils.Resource
import com.example.test_sportpro.utils.SportApplication
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class SportViewModel(
    app: Application,
    val sportRepository: SportRepository
) : AndroidViewModel(app) {

    val news: MutableLiveData<Resource<Article>> = MutableLiveData()
    var newsPage = 1
    var newsResponse: Article? = null

    val sport: MutableLiveData<Resource<SportType>> = MutableLiveData()
    val users: MutableLiveData<Resource<User>> = MutableLiveData()
    val trainers: MutableLiveData<Resource<TrainersList>> = MutableLiveData()
    val events: MutableLiveData<Resource<Events>> = MutableLiveData()
    val players: MutableLiveData<Resource<Player>> = MutableLiveData()
    val grids: MutableLiveData<Resource<Grids>> = MutableLiveData()

    fun getNews() = viewModelScope.launch {
        safeNewsCall()
    }

    fun getEvents(sport: Int, judge: Int) = viewModelScope.launch {
        events.postValue(Resource.Loading())
        val response = sportRepository.getEvents(sport, judge)
        events.postValue(handleEventsResponse(response))
    }

    fun getTrainerEvents(sport: Int) = viewModelScope.launch {
        events.postValue(Resource.Loading())
        val response = sportRepository.getTrainerEvents(sport)
        events.postValue(handleEventsResponse(response))
    }

    fun getFilteredNews(sport: Int) = viewModelScope.launch {
        news.postValue(Resource.Loading())
        val response = sportRepository.getFilteredNews(sport)
        news.postValue(handleNewsResponse(response))
    }

    fun getAllSport() = viewModelScope.launch {
        sport.postValue(Resource.Loading())
        val response = sportRepository.getAllSport()
        sport.postValue(handleSportResponse(response))
    }

    fun getSport(category: Int) = viewModelScope.launch {
        sport.postValue(Resource.Loading())
        val response = sportRepository.getSport(category)
        sport.postValue(handleSportResponse(response))
    }

    fun getUsers() = viewModelScope.launch {
        users.postValue(Resource.Loading())
        val response = sportRepository.getUsers()
        users.postValue(handleUsersResponse(response))
    }

    fun getJudges() = viewModelScope.launch {
        users.postValue(Resource.Loading())
        val response = sportRepository.getJudges()
        users.postValue(handleUsersResponse(response))
    }

    fun getTrainers() = viewModelScope.launch {
        trainers.postValue(Resource.Loading())
        val response = sportRepository.getTrainers()
        trainers.postValue(handleTrainersResponse(response))
    }

    fun getPlayers() = viewModelScope.launch {
        players.postValue(Resource.Loading())
        val response = sportRepository.getPlayers()
        players.postValue(handlePlayersResponse(response))
    }

    fun getGrids(event: Int) = viewModelScope.launch {
        grids.postValue(Resource.Loading())
        val response = sportRepository.getGrids(event)
        grids.postValue(handleGridsResponse(response))
    }

    private fun handleNewsResponse(response: Response<Article>) : Resource<Article> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                newsPage++
                if(newsResponse == null) {
                    newsResponse = resultResponse
                } else {
                    val oldArticles = newsResponse?.results
                    val newArticles = resultResponse.results
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleEventsResponse(response: Response<Events>) : Resource<Events> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSportResponse(response: Response<SportType>) : Resource<SportType> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleUsersResponse(response: Response<User>) : Resource<User> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleTrainersResponse(response: Response<TrainersList>) : Resource<TrainersList> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handlePlayersResponse(response: Response<Player>) : Resource<Player> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleGridsResponse(response: Response<Grids>) : Resource<Grids> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private suspend fun safeNewsCall() {
        news.postValue(Resource.Loading())
        try {
            if(hasInternetConnection()) {
                val response = sportRepository.getNews()
                news.postValue(handleNewsResponse(response))
            } else {
                news.postValue(Resource.Error("No internet connection"))
            }
        } catch(t: Throwable) {
            when(t) {
                is IOException -> news.postValue(Resource.Error("Network Failure"))
                else -> news.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<SportApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}