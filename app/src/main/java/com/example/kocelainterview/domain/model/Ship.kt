package com.example.kocelainterview.domain.model

import com.example.kocelainterview.data.localdatasource.ShipEntity

data class Ship(

    val active: Boolean,
   // val home_port: String,
    val image: String,
    val ship_id: String,
    //val ship_model: Any,
    val ship_name: String,
    //val ship_type: String,
//    val speed_kn: Int,
//    val status: String,
    val weight_kg: Int,
   // val url: String,
    //val weight_lbs: Int,
    val year_built: Int
) {
    fun toShipEntity():ShipEntity {
        return ShipEntity(
            id = 0, // Room will auto-generate id
            ship_id = this.ship_id,
            image = this.image,
            ship_name = this.ship_name,
            active = this.active,
            weight_kg = this.weight_kg,
            year_built = this.year_built
        )
    }
}
