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
    private val _recentBankNotes = MutableStateFlow<List<BankNote>>(emptyList())
    val recentBankNotes: StateFlow<List<BankNote>> = _recentBankNotes

    private val _randomBankNotes = MutableStateFlow<List<BankNote>>(emptyList())
    val randomBankNotes: StateFlow<List<BankNote>> = _randomBankNotes


    init {
        getBankNotes()
    }

    private fun getBankNotes(){
        viewModelScope.launch {
            try {
                repository.getBankNote(0).collect{
                    val response = it.data
                    _recentBankNotes.value = response.take(10)
                    _randomBankNotes.value = response.shuffled().take(10)
                }
            }catch (e : Exception){
                Log.d("Error getBankNotes", e.toString())
            }
        }
    }
}