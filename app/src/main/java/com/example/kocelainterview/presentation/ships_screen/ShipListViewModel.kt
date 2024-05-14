package com.example.kocelainterview.presentation.ships_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kocelainterview.common.core.Resource
import com.example.kocelainterview.domain.use_case.get_ships.GetShipsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShipListViewModel @Inject constructor(
    private val getShipsUseCase: GetShipsUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(ShipListState())
    val state: State<ShipListState> = _state

    init {
        getShips()
    }

    fun getShips(query: String = "") {
        viewModelScope.launch {
                getShipsUseCase(query) // No search query parameter
                    .onEach { result ->
                        when (result) {
                            is Resource.Success -> {
                                _state.value = ShipListState(ships = result.data ?: emptyList())
                            }
                            is Resource.Loading -> _state.value = ShipListState(isLoading = true)
                            is Resource.Error -> _state.value = ShipListState(error = result.message ?: "An unexpected error occurred")
                        }
                    }.launchIn(viewModelScope)
        }
    }

    fun updateSearchQuery(query: String) {
        _state.value = _state.value.copy(searchQuery = query)
        getShips(query) // Trigger data fetch with the new query
    }


}



