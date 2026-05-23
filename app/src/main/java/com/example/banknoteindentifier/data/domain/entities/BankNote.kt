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

    @SerializedName("_id")
    @PrimaryKey
    val id: String,
    @SerializedName("country_region")
    val countryRegion: String,
    val originURL: String,
    val title: String,
    val flag: String,
    val images: List<String>,
    @Embedded(prefix = "obverse")
    val obverse: Obverse,
    @Embedded(prefix = "reverse")
    val reverse: Reverse,
    val features: List<Features>,
    val pricing: List<Pricing>
) : Parcelable {

}