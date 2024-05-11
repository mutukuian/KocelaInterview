package com.example.kocelainterview.data.remote.dto

import com.example.kocelainterview.data.localdatasource.ShipsEntity
import com.example.kocelainterview.domain.model.Ship



data class ShipDto(
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

fun ShipDto.toShipEntity():ShipsEntity{
    return ShipsEntity(
        abs = abs,
        active = active,
        attempted_landings = attempted_landings,
        `class` = `class`,
        course_deg = course_deg,
        home_port = home_port,
        image = image,
        imo = imo,
        mmsi = mmsi,
        roles = roles,
        ship_id = ship_id,
        ship_model = ship_model,
        ship_name = ship_name,
        ship_type = ship_type,
        speed_kn = speed_kn,
        status = status,
        successful_landings = successful_landings,
        url = url,
        weight_kg = weight_kg,
        weight_lbs = weight_lbs,
        year_built = year_built
    )
}

fun ShipsEntity.toShip():Ship{
    return Ship(
        active = active, ship_id = ship_id, ship_name = ship_name, image = image, weight_kg = weight_kg, year_built = year_built
    )
}