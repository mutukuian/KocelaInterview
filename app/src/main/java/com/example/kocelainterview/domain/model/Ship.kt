package com.example.kocelainterview.domain.model



data class Ship(

    val active: Boolean,
    val image: String?,
    val ship_id: String,
    val ship_name: String,
    val weight_kg: Int,
    val year_built: Int
)
