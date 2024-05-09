package com.example.kocelainterview.domain.model

data class ShipDetail(
    val active: Boolean,
    val home_port: String,
    val image: String,
    val ship_id: String,
    val ship_model: Any,
    val ship_name: String,
    val ship_type: String,
    val speed_kn: Int,
    val status: String,
    val successful_landings: Any,
    val weight_kg: Int,
    val weight_lbs: Int,
    val year_built: Int
)
