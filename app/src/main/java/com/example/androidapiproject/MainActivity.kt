package com.example.androidapiproject

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.androidapiproject.databinding.ActivityMainBinding
import com.example.androidapiproject.model.PostModel
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    var list = arrayListOf<PostModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            postApiCall()
        }

    }

    fun apiCalling() {

        val link = "https://jsonplaceholder.typicode.com/posts"
        var request = Volley.newRequestQueue(this@MainActivity)

        var jsonArrayRequest = JsonArrayRequest(Request.Method.GET, link, null, {
            Log.e("TAG", "apiCalling: $it")
            var i = 0;
            while (i < it.length()) {
                var jsonObject = it!!.getJSONObject(i)
                var userId = jsonObject!!.getInt("userId")
                var id = jsonObject.getInt("id")
                var title = jsonObject.getString("title")
                var body = jsonObject.getString("body")
                var postModel = PostModel(id, userId, title, body);

                list.add(postModel)
                i++
            }

        }, {
            Log.e("TAG", "apiCalling: Error  $it")
        })

        request.add(jsonArrayRequest)

    }

    fun modelApiCall() {
        var link =
            "https://reqres.in/api/users?page=2"

        var request = Volley.newRequestQueue(this@MainActivity)

        var jsonObjectRequest = JsonObjectRequest(Request.Method.GET, link, null, {
              var jsonArray = it!!.getJSONArray("data")
            Log.e("TAG", "modelApiCall: $jsonArray" )
            var i=0
            while(i<jsonArray.length())
            {
                var jsonObject = jsonArray.getJSONObject(i);
                var id = jsonObject.getInt("id")
                var email = jsonObject.getString("email")
                var first_name = jsonObject.getString("first_name")
                var last_name = jsonObject.getString("last_name")
                var avatar = jsonObject.getString("avatar")

                i++
            }

        }, {
            Log.e("TAG", "modelApiCall: $it" )
        });

        request.add(jsonObjectRequest)

    }

    fun postApiCall() {
        var link="https://reqres.in/api/users"

        var request = Volley.newRequestQueue(this@MainActivity)

        var stringRequest = object : StringRequest(Request.Method.POST,link,{
            Log.e("TAG", "postApiCall: Success" )
        },{
            Log.e("TAG", "postApiCall: Failed" )
        }){
            override fun getParams(): MutableMap<String, String>? {
                var map:MutableMap<String,String> =HashMap()
                map["name" ]="jay"
                map["job"]="android"
                return map
            }
        }

        request.add(stringRequest)

    }



    /*
     * StringRequest
     * JsonObjectRequest
     * JsonArrayRequest
     * */


}