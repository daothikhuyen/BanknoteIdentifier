package com.example.banknoteindentifier.di.module

import com.example.banknoteindentifier.data.source.remote.ApiService
import com.example.banknoteindentifier.utils.AppConstant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private  const  val BASE_URL = AppConstant.BASE_URL_GET

    val apiService: ApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
}