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

    override suspend fun getBankNote(page : Int): Flow<BankNoteResponse> = flow {
        val response = apiService.getBankNote(page = page)
        emit(response)
    }

    override suspend fun getBankNoteById(id: String): BankNote = withContext(Dispatchers.IO) {
        val response = apiService.getBankNoteById(id)
        return@withContext response
    }

    override fun getCollection(): Flow<List<BankNote>> {
        return collectionBankNoteDao.getCollection()
    }

    override suspend fun getCollectionById(id: String): Flow<Boolean> = withContext(Dispatchers.IO) {
        return@withContext collectionBankNoteDao.getCollectionById(id)
    }

    override suspend fun toggleCollection(bankNote: BankNote) = withContext(Dispatchers.IO) {
        val collectionItem = collectionBankNoteDao.getCollectionItemOnce(bankNote.id)
        if (collectionItem == null) {
            collectionBankNoteDao.insertCollection(bankNote)
        } else {
            collectionBankNoteDao.deleteCollectionById(bankNote.id)
        }
    }

    override suspend fun searchByText(query: String, page: Int): List<BankNote> = withContext(Dispatchers.IO) {
        val response = apiService.searchBankNote(query, page)
        return@withContext response.data
    }

    override suspend fun searchByTextCollection(query: String): List<BankNote> = withContext(Dispatchers.IO) {
        val response = collectionBankNoteDao.searchByText(query)
        return@withContext response
    }
}