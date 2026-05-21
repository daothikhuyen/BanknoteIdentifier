package com.example.banknoteindentifier.data.source.remote

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

    @GET("banknotes/’id item’?debug=true")
    suspend fun getDetailBankNote(
        @Path("id item") id: String
    ): BankNoteResponse

    @GET("banknotes-search?title=&page=&debug=true")
    suspend fun searchBankNote(
        @Query("title") title: String,
        @Query("page") page: Int
    ): BankNoteResponse

}