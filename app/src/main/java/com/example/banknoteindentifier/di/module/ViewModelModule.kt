package com.example.banknoteindentifier.di.module

import com.example.banknoteindentifier.ui.collection.CollectionViewModel
import com.example.banknoteindentifier.ui.detail.DetailViewModel
import com.example.banknoteindentifier.ui.home.HomeViewModel
import com.example.banknoteindentifier.ui.search.SearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
    viewModelOf(::CollectionViewModel)
    viewModelOf(::SearchViewModel)
}