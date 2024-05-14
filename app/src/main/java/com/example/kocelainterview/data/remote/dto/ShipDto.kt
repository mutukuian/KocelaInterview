package com.example.kocelainterview.data.remote.dto


import com.example.kocelainterview.data.local_data_source.ShipEntity
import com.example.kocelainterview.domain.model.Ship



data class ShipDto(
    val active: Boolean,
    val image: String?,
    val ship_id: String,
    val ship_name: String,
    val weight_kg: Int,
    val year_built: Int
)

fun ShipDto.toEntity(): ShipEntity {
    return ShipEntity(
        ship_id = ship_id,
        active = active,
        image = image,
        ship_name = ship_name,
        weight_kg = weight_kg,
        year_built = year_built
    )
}





fun ShipDto.toShip():Ship{
    return Ship(
        active = active, ship_id = ship_id, ship_name = ship_name, image = image, weight_kg = weight_kg, year_built = year_built
    )
}