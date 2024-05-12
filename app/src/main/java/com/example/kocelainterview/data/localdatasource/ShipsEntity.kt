package com.example.kocelainterview.data.localdatasource

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ships")
data class ShipEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val ship_id: String,
    val image: String,
    val ship_name: String,
    val active: Boolean,
    val weight_kg: Int,
    val year_built: Int
)
