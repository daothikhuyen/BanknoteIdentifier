package com.example.banknoteindentifier.data.repository

import com.example.banknoteindentifier.data.domain.entities.BankNote
import com.example.banknoteindentifier.data.domain.entities.BankNoteResponse
import com.example.banknoteindentifier.data.domain.repository.BankNoteRepository
import com.example.banknoteindentifier.data.source.local.dao.CollectionBankNoteDao
import com.example.banknoteindentifier.data.source.remote.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BankNoteRepositoryImpl(private val apiService: ApiService, private val collectionBankNoteDao: CollectionBankNoteDao) : BankNoteRepository {
    override suspend fun getBankNote(): Flow<BankNoteResponse> = flow {
        val response = apiService.getBankNote(page = 1)
        emit(response)
    }

    override fun getCollectionById(id: String): Flow<BankNote> {
        return collectionBankNoteDao.getCollectionById(id)
    }

    override suspend fun toggleCollection(bankNote: BankNote): Flow<Boolean> {
        val collectionItem = collectionBankNoteDao.getCollectionItemOnce(bankNote.id)
        if (collectionItem == null) {
            collectionBankNoteDao.insertCollection(bankNote)
            return flow { emit(true) }
        }else{
            collectionBankNoteDao.deleteCollectionById(bankNote.id.toInt())
            return flow { emit(false) }
        }
    }

}