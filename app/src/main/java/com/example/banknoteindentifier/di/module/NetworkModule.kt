package com.example.banknoteindentifier.di.module

import com.example.banknoteindentifier.data.source.remote.ApiService
import com.example.banknoteindentifier.utils.AppConstant
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private fun provideAppApi(
    okHttpClient: OkHttpClient,
    converterJson: Converter.Factory
): ApiService {
    return Retrofit.Builder()
        .baseUrl(AppConstant.API_DOMAIN_SERVER)
        .client(okHttpClient)
        .addConverterFactory(converterJson)
        .build()
        .create(ApiService::class.java)
}

val networkModule = module {
    single {
        OkHttpClient.Builder().build()
    }
    single<Converter.Factory> {
        GsonConverterFactory.create()
    }
    singleOf(::provideAppApi)
}