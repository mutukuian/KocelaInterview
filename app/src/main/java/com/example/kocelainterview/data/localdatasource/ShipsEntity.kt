package com.example.kocelainterview.data.localdatasource

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShipsEntity(
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
    @PrimaryKey
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
