package com.example.banknoteindentifier.data.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Features(
    val title: String,
    val value: String
): Parcelable