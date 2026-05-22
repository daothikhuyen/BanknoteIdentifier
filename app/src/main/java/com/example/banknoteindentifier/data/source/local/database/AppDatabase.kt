package com.example.banknoteindentifier.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.banknoteindentifier.data.domain.entities.BankNote
import com.example.banknoteindentifier.data.source.local.dao.CollectionBankNoteDao

@Database(
    entities = [BankNote::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun collectionBankNoteDao(): CollectionBankNoteDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "banknote.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}