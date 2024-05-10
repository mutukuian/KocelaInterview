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
import javax.inject.Inject

@HiltViewModel
class ShipListViewModel @Inject constructor(
    private val getShipsUseCase: GetShipsUseCase
) : ViewModel(){
    private val _state = mutableStateOf(ShipListState())
    val state:State<ShipListState> = _state

    init {
        getShips()
    }
    private fun getShips(){
        getShipsUseCase().onEach{result->
            when(result){
                is Resource.Success->{_state.value =ShipListState(ships = result.data?: emptyList())}
                is Resource.Loading->{_state.value =ShipListState(isLoading = true)}
                is Resource.Error ->{_state.value =ShipListState(error = result.message?:"An unexpected error occurred")}
            }
        }.launchIn(viewModelScope)
    }
}