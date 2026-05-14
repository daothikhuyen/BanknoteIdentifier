package com.example.banknoteindentifier.data.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BankNoteResponse(
    val data: List<BankNote>,
    val hasNextPage: Boolean,
    val totalPage: Int
): Parcelable {
}