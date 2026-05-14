package com.example.banknoteindentifier.data.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BankNote(

    @SerializedName("_id")
    val id: String,
    @SerializedName("country_region")
    val countryRegion: String,
    val originURL: String,
    val title: String,
    val flag: String,
    val images: ArrayList<String>,
    val obverse: Obverse,
    val reverse: Reverse,
    val features: ArrayList<Features>,
    val pricing: ArrayList<Pricing>
) : Parcelable {

}