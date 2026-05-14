package com.example.banknoteindentifier.data.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pricing(
    val platform : String? = null,
    val image    : String? = null,
    val title    : String? = null,
    val price    : String? = null,
): Parcelable{
}