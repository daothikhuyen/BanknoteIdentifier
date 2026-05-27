package com.example.banknoteindentifier.di.module

import com.example.banknoteindentifier.data.domain.repository.BankNoteRepository
import com.example.banknoteindentifier.data.repository.BankNoteRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::BankNoteRepositoryImpl){
        bind<BankNoteRepository>()
    }
}