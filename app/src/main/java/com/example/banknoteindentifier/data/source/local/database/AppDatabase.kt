package com.example.banknoteindentifier.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.banknoteindentifier.data.domain.entities.BankNote
import com.example.banknoteindentifier.data.source.local.dao.CollectionBankNoteDao

@Database(
    entities = [BankNote::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun collectionBankNoteDao(): CollectionBankNoteDao

}