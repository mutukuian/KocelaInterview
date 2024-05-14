package com.example.kocelainterview.domain.model

data class ShipDetail(
    val active: Boolean,
    val image: String?,
    val home_port: String,
    val ship_id: String,
    val ship_name: String,
    val ship_type: String,
    val weight_kg: Int,
    val year_built: Int
)
