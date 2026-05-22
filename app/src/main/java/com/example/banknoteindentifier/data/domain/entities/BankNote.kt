package com.example.banknoteindentifier.data.domain.entities

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "collection")
data class BankNote(

    @PrimaryKey
    @SerializedName("_id")
    val id: String,
    @SerializedName("country_region")
    val countryRegion: String,
    val originURL: String,
    val title: String,
    val flag: String,
    @Embedded
    val images: ArrayList<String>,
    val obverse: Obverse,
    val reverse: Reverse,
    @Embedded
    val features: ArrayList<Features>,
    @Embedded
    val pricing: ArrayList<Pricing>
) : Parcelable {

}