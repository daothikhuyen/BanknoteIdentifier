package com.example.banknoteindentifier.di

import com.example.banknoteindentifier.data.domain.repository.BankNoteRepository
import com.example.banknoteindentifier.data.repository.BankNoteRepositoryImpl
import com.example.banknoteindentifier.di.module.RetrofitClient
import com.example.banknoteindentifier.ui.home.HomeViewModel
import org.koin.dsl.module

val appModule = module {

    single { RetrofitClient.apiService }

    single<BankNoteRepositoryImpl> { BankNoteRepositoryImpl(get())}
    single<BankNoteRepository>{ BankNoteRepositoryImpl(get())}

    single { HomeViewModel(get()) }


}