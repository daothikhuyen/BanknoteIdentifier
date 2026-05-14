package com.example.banknoteindentifier.data.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Reverse(
    val script: String,
    val lettering: String,
    val translation: String,
    val description: String
): Parcelable