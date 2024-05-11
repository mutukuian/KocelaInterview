package com.example.kocelainterview.data.localdatasource

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ShipDao {
    @Upsert
    suspend fun upsertAll(ships:List<ShipsEntity>)

    @Query("SELECT * FROM shipsentity")
    fun pagingSource():PagingSource<Int ,ShipsEntity>

    @Query("DELETE FROM shipsentity")
    suspend fun clearAll()
}