package com.example.kocelainterview.data.remote.dto

import com.example.kocelainterview.domain.model.ShipDetail

data class ShipDetailDto(
    val abs: Int,
    val active: Boolean,
    val attempted_landings: Any,
    val `class`: Int,
    val course_deg: Any,
    val home_port: String,
    val image: String,
    val imo: Int,
    val mmsi: Int,
    val roles: List<String>,
    val ship_id: String,
    val ship_model: Any,
    val ship_name: String,
    val ship_type: String,
    val speed_kn: Int,
    val status: String,
    val successful_landings: Any,
    val url: String,
    val weight_kg: Int,
    val weight_lbs: Int,
    val year_built: Int
)

fun ShipDetailDto.toShipDetail():ShipDetail{
    return ShipDetail(active, home_port, ship_id, ship_name, ship_type, weight_kg, year_built)
}