package com.example.kocelainterview.data.localdatasource


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ShipDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShips(ships: List<ShipEntity>)

    @Query("SELECT * FROM ships")
    suspend fun getCachedShips(): List<ShipEntity>

    @Delete
    suspend fun clearCache()
}