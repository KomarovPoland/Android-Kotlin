package com.example.test_sportpro.api

import com.example.test_sportpro.models.DefaultResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import com.example.test_sportpro.models.*


interface NewsApi {

    @GET("api/news")
    suspend fun getNews(): Response<Article>

    @GET("api/news")
    suspend fun getFilteredNews(
        @Query("sport")
        sport: Int
    ): Response<Article>

    @GET("api/sport")
    suspend fun getAllSport(): Response<SportType>

    @GET("api/sport")
    suspend fun getSport(
        @Query("category")
        category: Int
    ): Response<SportType>

    @GET("api/user")
    suspend fun getUsers(): Response<User>

    @GET("api/userbyrole/3")
    suspend fun getJudges(): Response<User>

    @GET("api/trainers")
    suspend fun getTrainers(): Response<TrainersList>

    @GET("api/players")
    suspend fun getPlayers(): Response<Player>

    @GET("api/grids")
    suspend fun getGrids(
        @Query("event")
        event: Int
    ): Response<Grids>

    @PATCH("api/matches/{id}/set_score/")
    fun updateScore(
        @Path("id") id: Int,
        @Body request: ScoreRequest
    ) : Call<Matche>

    @FormUrlEncoded
    @POST("api/user/")
    fun createUser(
        @Field("name") name: String,
        @Field("surname") surname: String,
        @Field("middlename") middlename: String,
        @Field("phone") phone: String,
        @Field("role") role: Int,
        @Field("region") region: Int,
        @Field("organization") organization: String,
        @Field("sport") sport: Int,
        @Field("document") document: String,
        @Field("photo") photo: String
    ): Call<DefaultResponse>


    @FormUrlEncoded
    @POST("api/registerplayers")
    fun createPlayerList(
        @Field("players") players_list: MutableList<Int>,
        @Field("event") event_id: Int,
    ): Call<PlayerList>


    @GET("api/event")
    suspend fun getEvents(
        @Query("sport")
        sport: Int,
        @Query("judge")
        judge: Int
    ): Response<Events>

    @GET("api/event")
    suspend fun getTrainerEvents(
        @Query("sport")
        sport: Int
    ): Response<Events>

    @POST("api/players/")
    fun createNewPlayer(@Body request: PlayersRequest): Call<DefaultResponsePlayer>

    @POST("api/user/log")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}
