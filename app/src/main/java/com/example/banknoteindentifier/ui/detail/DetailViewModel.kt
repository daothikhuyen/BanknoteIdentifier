package com.example.banknoteindentifier.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banknoteindentifier.data.domain.entities.BankNote
import com.example.banknoteindentifier.data.domain.repository.BankNoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: BankNoteRepository) : ViewModel() {

    private val _isCollection = MutableStateFlow<Boolean>(false)
    val isCollection: Flow<Boolean> = _isCollection

    fun getCollectionById(id: String){
        viewModelScope.launch {
            try {
                Log.d("id", id)
                repository.getCollectionById(id).collect { isExisted ->
                    _isCollection.value = isExisted
                }
            }catch (e : Exception){
                Log.d("error", e.toString())
            }
        }
    }

    fun toggleCollection(bankNote: BankNote){
        viewModelScope.launch {
            repository.toggleCollection(bankNote)
        }
    }
}