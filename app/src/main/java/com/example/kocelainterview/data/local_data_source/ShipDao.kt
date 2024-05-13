package com.example.kocelainterview.data.local_data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShipDao {
    @Query("SELECT * FROM ships")
    fun getShips(): List<ShipEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveShips(ships: List<ShipEntity>)
}