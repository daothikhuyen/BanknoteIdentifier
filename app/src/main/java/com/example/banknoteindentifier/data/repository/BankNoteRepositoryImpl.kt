package com.example.banknoteindentifier.data.repository

import com.example.banknoteindentifier.data.domain.entities.BankNoteResponse
import com.example.banknoteindentifier.data.domain.repository.BankNoteRepository
import com.example.banknoteindentifier.data.source.remote.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BankNoteRepositoryImpl(private val apiService: ApiService) : BankNoteRepository {
    override suspend fun getBankNote(): Flow<BankNoteResponse> = flow {
        val response = apiService.getBankNote(page = 1)
        emit(response)
    }
}