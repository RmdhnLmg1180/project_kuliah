package com.kuliah.apidata.data.remote

import android.telecom.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/Users")
    fun getusers(): Call<List<UsersItem>>
}