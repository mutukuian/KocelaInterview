package com.example.kocelainterview.presentation.search_controller.search_screen

import com.example.kocelainterview.domain.model.Ship

sealed class SearchState {
    object Empty : SearchState()
    object Loading : SearchState()
    data class Success(val ships: List<Ship>) : SearchState()
    data class Error(val message: String) : SearchState()
}