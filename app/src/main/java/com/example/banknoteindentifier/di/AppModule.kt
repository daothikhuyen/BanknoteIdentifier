package com.example.banknoteindentifier.di

import com.example.banknoteindentifier.data.domain.repository.BankNoteRepository
import com.example.banknoteindentifier.data.repository.BankNoteRepositoryImpl
import com.example.banknoteindentifier.data.source.local.database.AppDatabase
import com.example.banknoteindentifier.di.module.RetrofitClient
import com.example.banknoteindentifier.ui.collection.CollectionViewModel
import com.example.banknoteindentifier.ui.detail.DetailViewModel
import com.example.banknoteindentifier.ui.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    single { RetrofitClient.apiService }
    single { AppDatabase.getInstance(androidContext()) }
    single { get<AppDatabase>().collectionBankNoteDao() }

    single<BankNoteRepositoryImpl> { BankNoteRepositoryImpl(get(), get())}
    single<BankNoteRepository>{ BankNoteRepositoryImpl(get(), get())}

    single { HomeViewModel(get()) }
    single { DetailViewModel(get()) }
    single { CollectionViewModel(get()) }


}