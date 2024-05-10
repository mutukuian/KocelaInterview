package com.example.kocelainterview.presentation.ships_screen

import com.example.kocelainterview.domain.model.Ship

data class ShipListState(
    val isLoading:Boolean = false,
    val ships:List<Ship> = emptyList(),
    val error:String = ""
)
