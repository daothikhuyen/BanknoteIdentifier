package com.example.banknoteindentifier.ui.collection

import androidx.lifecycle.ViewModel
import com.example.banknoteindentifier.data.domain.repository.BankNoteRepository

class CollectionViewModel(private val repository: BankNoteRepository): ViewModel() {
    val collections = repository.getCollection()
}