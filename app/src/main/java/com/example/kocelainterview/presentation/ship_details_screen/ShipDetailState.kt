package com.example.kocelainterview.presentation.ship_details_screen

import com.example.kocelainterview.domain.model.ShipDetail

data class ShipDetailState(
    val isLoading:Boolean = false,
    val ship:ShipDetail? = null,
    val error:String = ""
)
