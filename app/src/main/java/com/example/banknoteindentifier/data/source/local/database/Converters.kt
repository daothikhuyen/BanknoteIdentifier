package com.example.banknoteindentifier.data.source.local.database

import androidx.room.TypeConverter
import com.example.banknoteindentifier.data.domain.entities.Features
import com.example.banknoteindentifier.data.domain.entities.Pricing
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromFeaturesList(value: List<Features>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toFeaturesList(value: String): List<Features> {
        val type = object : TypeToken<List<Features>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromPricingList(value: List<Pricing>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toPricingList(value: String): List<Pricing> {
        val type = object : TypeToken<List<Pricing>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromImageList(value: List<String>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toImageList(value: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }
}