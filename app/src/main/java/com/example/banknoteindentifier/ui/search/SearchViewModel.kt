package com.example.banknoteindentifier.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banknoteindentifier.data.domain.entities.BankNote
import com.example.banknoteindentifier.data.domain.repository.BankNoteRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val bankNoteRepository: BankNoteRepository) : ViewModel() {
    private val _searchBankNotes = MutableStateFlow<List<BankNote>>(emptyList())
    val searchBankNotes = _searchBankNotes

    private val _isLoadingMore = MutableStateFlow(false)
    val isLoadingMore = _isLoadingMore.asStateFlow()

    private var currentPage = 0
    private var isLastPage = false
    private var currentKeyword = ""

    init {
        loadData()
    }

    private suspend fun getBankNotes() {
        try {
            bankNoteRepository.getBankNote(currentPage).collect {
                val result = it.data
                val currentList = _searchBankNotes.value.toMutableList()
                currentList.addAll(result)
                delay(1000)
                _searchBankNotes.value = currentList

                if (result.isEmpty()) {
                    isLastPage = true
                } else {
                    currentPage++
                }
            }
        }catch (e : Exception){
            Log.d("error getBankNotes", e.toString())
            isLastPage = true
        }
    }

    fun loadData() {
        viewModelScope.launch {
            try {
                getBankNotes()
            } catch (e: Exception) {
                Log.d("error loadAllData", e.toString())
            }
        }

    }

    fun onLoadMore() {
        if (_isLoadingMore.value || isLastPage) return

        viewModelScope.launch {
            try {
                _isLoadingMore.value = true
                if (currentKeyword.isBlank()) {
                    getBankNotes()
                } else {
                    onSubmitSearch(currentKeyword)
                }
            } catch (e: Exception) {
                Log.d("error loadMore", e.toString())
            } finally {
                _isLoadingMore.value = false
            }
        }
    }

    fun onSubmitSearch(keyword: String) {
        try {
            viewModelScope.launch {
                val result = bankNoteRepository.searchByText(keyword, currentPage)
                _searchBankNotes.value = result
            }
        }catch (e : Exception){
            Log.d("error onSubmitSearch", e.toString())
        }
    }

    fun clearSearch() {
        currentKeyword = ""
        currentPage = 0
        isLastPage = false
        _isLoadingMore.value = false
        _searchBankNotes.value = emptyList()
        loadData()
    }
}