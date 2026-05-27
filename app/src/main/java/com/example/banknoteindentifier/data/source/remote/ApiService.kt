package com.example.banknoteindentifier.data.source.remote

import com.example.banknoteindentifier.data.domain.entities.BankNote
import com.example.banknoteindentifier.data.domain.entities.BankNoteResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("banknotes")
    suspend fun getBankNote(
        @Query("debug") debug: Boolean = true,
        @Query("page") page: Int
    ): BankNoteResponse

    @GET("banknotes/{id}")
    suspend fun getBankNoteById(
        @Path("id") id: String,
        @Query("debug") debug: Boolean = true
    ): BankNote

    @GET("banknotes-search")
    suspend fun searchBankNote(
        @Query("title") title: String,
        @Query("page") page: Int,
        @Query("debug") debug: Boolean = true,
    ): BankNoteResponse

}