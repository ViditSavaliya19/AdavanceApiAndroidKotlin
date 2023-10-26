package com.example.androidapiproject.api_helper


import com.example.androidapiproject.model.Language
import com.example.androidapiproject.model.NewsModel
import com.example.androidapiproject.model.TransModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @GET("top-headlines")
    fun getNews(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") api: String
    ): Call<NewsModel>

    @POST("translate")
    fun getTranslate(
        @Header("X-RapidAPI-Key") h1: String,
        @Header("X-RapidAPI-Host") h2: String,
        @Body model:TransModel
    ): Call<Language>
}