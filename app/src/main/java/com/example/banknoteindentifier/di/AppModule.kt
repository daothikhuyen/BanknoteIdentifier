package com.example.banknoteindentifier.di

import com.example.banknoteindentifier.di.module.databaseModule
import com.example.banknoteindentifier.di.module.networkModule
import com.example.banknoteindentifier.di.module.repositoryModule
import com.example.banknoteindentifier.di.module.viewModelModule
import org.koin.dsl.module

val appModule = listOf(
    networkModule,
    repositoryModule,
    viewModelModule,
    databaseModule
)