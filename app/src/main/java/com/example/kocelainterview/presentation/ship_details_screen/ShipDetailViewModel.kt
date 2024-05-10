package com.example.kocelainterview.presentation.ship_details_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kocelainterview.common.core.Constants.PARAM_SHIP_ID
import com.example.kocelainterview.common.core.Resource
import com.example.kocelainterview.domain.use_case.get_ship.GetShipUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ShipDetailViewModel @Inject constructor(
    private val getShipUseCase: GetShipUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _state = mutableStateOf(ShipDetailState())
    val state:State<ShipDetailState> = _state

    init {
        savedStateHandle.get<String>(PARAM_SHIP_ID)?.let { shipId->
            getShip(shipId)
        }
    }
    private fun getShip(shipId:String){
        getShipUseCase(shipId).onEach{result->
            when(result){
                is Resource.Success->{_state.value =ShipDetailState(ship = result.data)}
                is Resource.Loading->{_state.value =ShipDetailState(isLoading = true)}
                is Resource.Error ->{_state.value =ShipDetailState(error = result.message?:"An unexpected error occurred")}
            }
        }.launchIn(viewModelScope)
    }
}