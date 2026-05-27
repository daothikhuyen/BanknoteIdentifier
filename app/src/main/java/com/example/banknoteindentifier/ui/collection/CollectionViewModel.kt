package com.example.banknoteindentifier.ui.collection

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banknoteindentifier.data.domain.entities.BankNote
import com.example.banknoteindentifier.data.domain.repository.BankNoteRepository
import com.example.banknoteindentifier.data.source.local.dao.CollectionBankNoteDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CollectionViewModel(private val dao: CollectionBankNoteDao): ViewModel() {

    private val _collections = MutableStateFlow<List<BankNote>>(emptyList())
    val collections = _collections

    init {
        getCollections()
    }

    fun getCollections(){
        viewModelScope.launch {
            try {
                dao.getCollection().collect{
                    _collections.value = it
                }
            }catch (e : Exception){
                Log.d("error getCollections", e.toString())
                _collections.value = emptyList()
            }
        }
    }


    fun onSubmitSearch(keyword : String){
        viewModelScope.launch {
            try {
                val result = dao.searchByText(keyword)
                _collections.value = result
            }catch (e : Exception){
                Log.d("error onSubmitSearch", e.toString())
                _collections.value = emptyList()
            }
        }
    }
}