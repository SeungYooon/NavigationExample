package com.example.navigation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.navigation.repository.Repository

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelSearch::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ViewModelSearch(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}