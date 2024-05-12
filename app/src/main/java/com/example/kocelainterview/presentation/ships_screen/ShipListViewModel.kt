package com.example.kocelainterview.presentation.ships_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kocelainterview.common.core.Resource
import com.example.kocelainterview.data.localdatasource.ShipDao
import com.example.kocelainterview.data.localdatasource.ShipEntity
import com.example.kocelainterview.data.remote.dto.ShipDto
import com.example.kocelainterview.data.remote.dto.toShip
import com.example.kocelainterview.domain.model.Ship
import com.example.kocelainterview.domain.use_case.get_ships.GetShipsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShipListViewModel @Inject constructor(
    private val getShipsUseCase: GetShipsUseCase,
    private val shipDao: ShipDao
) : ViewModel() {
    private val _state = mutableStateOf(ShipListState())
    val state: State<ShipListState> = _state

    init {
        getShips()
    }

    private fun getShips() {
        viewModelScope.launch {
            val cachedShips = shipDao.getCachedShips()
            if (cachedShips.isNotEmpty()) {
                _state.value = _state.value.copy(ships = cachedShips.map { it.toShip() })
            } else {
                getShipsUseCase() // No search query parameter
                    .onEach { result ->
                        when (result) {
                            is Resource.Success -> {
                                val shipEntities = result.data?.map {it.toShipEntity() } ?: emptyList()
                                shipDao.insertShips(shipEntities) // Fix type mismatch
                                _state.value = ShipListState(ships = result.data ?: emptyList())
                            }
                            is Resource.Loading -> _state.value = ShipListState(isLoading = true)
                            is Resource.Error -> _state.value = ShipListState(error = result.message ?: "An unexpected error occurred")
                        }
                    }
                    .launchIn(viewModelScope)
            }
        }
    }

}

