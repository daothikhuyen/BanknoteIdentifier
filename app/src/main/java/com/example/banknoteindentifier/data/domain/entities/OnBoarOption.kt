package com.example.banknoteindentifier.data.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OnboardingPage(
    val title: String,
    val options: List<OnboardingOption>
): Parcelable

@Parcelize
data class OnboardingOption(
    val id: Int,
    val imgRes: Int,
    val title: String,
    val description: String = "",
    val isSelected: Boolean = false
): Parcelable