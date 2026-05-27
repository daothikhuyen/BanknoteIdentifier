package com.example.banknoteindentifier.di.module

import android.content.Context
import androidx.room.Room
import com.example.banknoteindentifier.data.source.local.dao.CollectionBankNoteDao
import com.example.banknoteindentifier.data.source.local.database.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun provideDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "banknote.db"
    ).build()
}

fun provideCollectionBankNoteDao(database: AppDatabase) : CollectionBankNoteDao {
    return database.collectionBankNoteDao()
}

val databaseModule = module {
    single { provideDatabase(androidApplication()) }
    singleOf(::provideCollectionBankNoteDao)

}