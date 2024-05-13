package com.example.kocelainterview.data.local_data_source

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kocelainterview.data.remote.dto.ShipDto

@Entity(tableName = "ships")
data class ShipEntity(

    val ship_id: String,
    val active: Boolean,
    val image: String?,
    val ship_name: String,
    @PrimaryKey
    val weight_kg: Int,
    val year_built: Int
)


fun ShipEntity.toShipDto(): ShipDto {
    return ShipDto(
        ship_id = ship_id,
        active = active,
        image = image,
        ship_name = ship_name,
        weight_kg = weight_kg,
        year_built = year_built

    )
}




