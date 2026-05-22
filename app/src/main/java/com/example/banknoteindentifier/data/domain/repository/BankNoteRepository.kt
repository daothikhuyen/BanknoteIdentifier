package com.example.banknoteindentifier.data.domain.repository

import com.example.banknoteindentifier.data.domain.entities.BankNote
import com.example.banknoteindentifier.data.domain.entities.BankNoteResponse
import kotlinx.coroutines.flow.Flow

interface BankNoteRepository {

    suspend fun getBankNote() : Flow<BankNoteResponse>
    fun getCollectionById(id : String) : Flow<BankNote>

    suspend fun toggleCollection(bankNote: BankNote) : Flow<Boolean>
}