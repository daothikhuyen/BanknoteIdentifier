package com.example.banknoteindentifier.ui.onboar

import androidx.lifecycle.ViewModel
import com.example.banknoteindentifier.data.domain.entities.OnboardingOption
import com.example.banknoteindentifier.data.domain.entities.OnboardingPage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.concurrent.atomics.update

class OnBoarViewModel() : ViewModel() {

    private var _option = MutableStateFlow<Map<Int,List<OnboardingOption>>>(emptyMap())
    val option = _option.asStateFlow()

    private var _isNextEnable = MutableStateFlow(false)
    val isNextEnable = _isNextEnable.asStateFlow()

    fun setUpData(type : Int, dataBoar: OnboardingPage){
        if (!_option.value.containsKey(type)) {
            _option.update { currentMap ->
                currentMap + (type to dataBoar.options)
            }
        }
    }

    fun onClickOption(type : Int, selectOption: OnboardingOption) {
        _option.update { currentMap ->
            val currentOptions = currentMap[type] ?: return@update currentMap
            val updatedOptions = currentOptions.map { option ->
                option.copy(isSelected = option.id == selectOption.id)
            }
            currentMap + (type to updatedOptions)
        }
        checkNextEnable(type)

    }

    fun checkNextEnable(type: Int){
        _isNextEnable.value = _option.value[type]?.any { it.isSelected } ?: false
    }
}
