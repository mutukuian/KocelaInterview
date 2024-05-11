package com.example.kocelainterview.data.localdatasource

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ShipsEntity::class],
    version = 1
)
abstract class ShipDatabase:RoomDatabase() {

    abstract val dao:ShipDao
}