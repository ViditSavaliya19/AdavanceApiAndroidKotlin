package com.example.androidapiproject.api_helper

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
//        val BASE_URL="https://newsapi.org/v2/"
        val BASE_URL="https://text-translator2.p.rapidapi.com/"

        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }

}