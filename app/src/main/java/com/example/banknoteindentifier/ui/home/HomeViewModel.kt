package com.example.banknoteindentifier.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banknoteindentifier.data.domain.entities.BankNote
import com.example.banknoteindentifier.data.domain.repository.BankNoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: BankNoteRepository) : ViewModel() {
    private val _bankNotes = MutableStateFlow<List<BankNote>>(emptyList())
    val bankNotes: StateFlow<List<BankNote>> = _bankNotes

    init {
        getBankNotes()
    }

    private fun getBankNotes(){
        viewModelScope.launch {
            repository.getBankNote().collect{
                _bankNotes.value = it.data
            }
        }
    }
}