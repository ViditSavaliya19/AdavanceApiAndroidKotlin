package com.example.androidapiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.androidapiproject.api_helper.ApiClient.Companion.getRetrofit
import com.example.androidapiproject.api_helper.ApiInterface
import com.example.androidapiproject.model.ArticlesItem
import com.example.androidapiproject.model.Language
import com.example.androidapiproject.model.NewsModel
import com.example.androidapiproject.model.TransModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class MainActivity2 : AppCompatActivity() {
    var articlesList: ArrayList<ArticlesItem?>? = arrayListOf<ArticlesItem?>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
//        getNewsData("us", "sport")
        getData()
    }

    fun getNewsData(country: String, category: String) {
        var apiInterface = getRetrofit().create(ApiInterface::class.java)
        apiInterface.getNews(country, category, "1ee831619156425192704ed881bf244e")
            .enqueue(object : Callback<NewsModel> {
                override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                    var newsModel = response.body()
                    articlesList = newsModel!!.articles as ArrayList<ArticlesItem?>?
                    Log.e("TAG", "onResponse: ${articlesList!!.get(0)!!.title}")
                }

                override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                    Log.e("TAG", "onFailure: $t")
                }
            })
    }

    fun getData() {
        var model = TransModel("en", "hi", "How are you?")
        var apiInterface = getRetrofit().create(ApiInterface::class.java)
        apiInterface.getTranslate(
            "637c4831ffmsh0639c4da9b0ecbap197ad7jsn39a7c0327946",
            "text-translator2.p.rapidapi.com",
            model
        ).enqueue(object  : Callback<Language>{
            override fun onResponse(call: Call<Language>, response: Response<Language>) {
                Log.e("TAG", "onResponse: ${response.body()!!.data!!.translatedText}" )
            }

            override fun onFailure(call: Call<Language>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}" )
            }
        })
    }
}