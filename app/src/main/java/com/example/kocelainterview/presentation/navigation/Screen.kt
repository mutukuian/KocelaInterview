package com.example.kocelainterview.presentation.navigation

sealed class Screen(val route:String) {
    object ShipListScreen:Screen("ship_list")
    object ShipDetailScreen:Screen("ship_detail")
}