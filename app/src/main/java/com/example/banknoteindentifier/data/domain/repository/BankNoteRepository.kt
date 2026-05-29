package com.example.banknoteindentifier.data.domain.repository

import com.example.banknoteindentifier.data.domain.entities.BankNote
import com.example.banknoteindentifier.data.domain.entities.BankNoteResponse
import kotlinx.coroutines.flow.Flow

interface BankNoteRepository {

    suspend fun getBankNote(page: Int) : BankNoteResponse

    suspend fun getBankNoteById(id : String) : BankNote

    fun getCollection() : Flow<List<BankNote>>

    suspend fun getCollectionById(id : String) : Flow<Boolean>

    suspend fun toggleCollection(bankNote: BankNote)

    suspend fun searchByText(query : String, page: Int) : List<BankNote>

    suspend fun searchByTextCollection(query : String) : List<BankNote>
}