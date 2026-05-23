package com.example.banknoteindentifier.data.repository

import android.util.Log
import com.example.banknoteindentifier.data.domain.entities.BankNote
import com.example.banknoteindentifier.data.domain.entities.BankNoteResponse
import com.example.banknoteindentifier.data.domain.repository.BankNoteRepository
import com.example.banknoteindentifier.data.source.local.dao.CollectionBankNoteDao
import com.example.banknoteindentifier.data.source.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class BankNoteRepositoryImpl(
    private val apiService: ApiService,
    private val collectionBankNoteDao: CollectionBankNoteDao
) : BankNoteRepository {

    override suspend fun getBankNote(): Flow<BankNoteResponse> = flow {
        val response = apiService.getBankNote(page = 1)
        emit(response)
    }

    override fun getCollection(): Flow<List<BankNote>> {
        return collectionBankNoteDao.getCollection()
    }

    override fun getCollectionById(id: String): Flow<Boolean> {
        return collectionBankNoteDao.getCollectionById(id)
    }

    override suspend fun toggleCollection(bankNote: BankNote) = withContext(Dispatchers.IO) {
        val collectionItem = collectionBankNoteDao.getCollectionItemOnce(bankNote.id)
        Log.d("collectionItem", collectionItem.toString())
        if (collectionItem == null) {
            collectionBankNoteDao.insertCollection(bankNote)
        } else {
            collectionBankNoteDao.deleteCollectionById(bankNote.id)
        }
    }
}