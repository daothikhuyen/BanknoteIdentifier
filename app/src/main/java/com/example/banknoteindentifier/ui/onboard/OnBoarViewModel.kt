package com.example.banknoteindentifier.ui.onboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnBoarViewModel() : ViewModel() {

    private var _option = MutableStateFlow<Int?>(null)
    val option = _option.asStateFlow()

    private var _isNextEnable = MutableStateFlow(false)
    val isNextEnable = _isNextEnable.asStateFlow()

    fun selectOption(option: Int){
        _option.value = option
        _isNextEnable.value = true
    }

    fun reset(){
        _option.value = null
        _isNextEnable.value = false
    }
}
