package com.example.banknoteindentifier.data.source.local.preference

import android.content.Context

class SharedPrefKey(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

}