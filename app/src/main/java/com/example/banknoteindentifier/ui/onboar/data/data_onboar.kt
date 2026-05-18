package com.example.banknoteindentifier.ui.onboar.data

import com.example.banknoteindentifier.R
import com.example.banknoteindentifier.data.domain.entities.OnboardingOption
import com.example.banknoteindentifier.data.domain.entities.OnboardingPage

fun getDataOnBoarFirst(): OnboardingPage{
    return OnboardingPage(
        title = "What kind of stones attract you most?",
        options = listOf(
            OnboardingOption(
                id = 1,
                imgRes = R.drawable.img_banknote,
                title = "Banknote",
                description = "Paper bills & collectible notes",
                isSelected = false
            ),
            OnboardingOption(
                id = 2,
                imgRes = R.drawable.img_coin,
                title = "Coin",
                description = "Modern & old coins"
            ),
            OnboardingOption(
                id = 3,
                imgRes = R.drawable.img_both,
                title = "Both",
                description = "I collect everything."
            )
        )
    )
}


fun getDataOnBoarTwo(): OnboardingPage{
    return OnboardingPage(
        title = "What do you want to use the app for?",
        options = listOf(
            OnboardingOption(
                id = 1,
                imgRes = R.drawable.ic_scan_32dp,
                title = "Identify banknotes",
            ),
            OnboardingOption(
                id = 2,
                imgRes = R.drawable.ic_scan_32dp,
                title = "Identify coins",
            ),
            OnboardingOption(
                id = 3,
                imgRes = R.drawable.ic_zoom_money_32dp,
                title = "Check collector value",
            ),
            OnboardingOption(
                id = 4,
                imgRes = R.drawable.ic_book_32dp,
                title = "Learn about currencies",
            )
        )
    )
}

fun getDataOnBoarThree(): OnboardingPage{
    return OnboardingPage(
        title = "How do you prefer your results displayed?",
        options = listOf(
            OnboardingOption(
                id = 1,
                imgRes = R.drawable.ic_simple_bold_32dp,
                title = "Simple & easy to read",
            ),
            OnboardingOption(
                id = 2,
                imgRes = R.drawable.ic_list_detail_32dp,
                title = "Full detailed info",
            ),
            OnboardingOption(
                id = 3,
                imgRes = R.drawable.ic_money_message_26dp,
                title = "Focus on collector value",
            ),
            OnboardingOption(
                id = 3,
                imgRes = R.drawable.ic_oir_book_32dp,
                title = "Focus on history & origin",
            )
        )
    )
}