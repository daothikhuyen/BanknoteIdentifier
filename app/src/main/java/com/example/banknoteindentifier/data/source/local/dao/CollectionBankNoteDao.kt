package com.example.banknoteindentifier.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.banknoteindentifier.data.domain.entities.BankNote
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectionBankNoteDao {

    @Query("SELECT * FROM collection")
    fun getCollection(): Flow<List<BankNote>>

    @Query("SELECT EXISTS(SELECT 1 FROM collection WHERE id = :id)")
    fun getCollectionById(id : String): Flow<Boolean>

    @Insert
    suspend fun insertCollection(collection: BankNote)

    @Query("DELETE FROM collection WHERE id = :id")
    suspend fun deleteCollectionById(id: String)

    @Query("SELECT * FROM collection WHERE id = :id")
    fun getCollectionItemOnce(id : String): BankNote?

}