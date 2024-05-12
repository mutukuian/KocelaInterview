package com.example.kocelainterview.data.localdatasource

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ShipEntity::class],
    version = 1
)
abstract class ShipDatabase : RoomDatabase() {
    abstract val shipDao: ShipDao

    companion object {
        const val DATABASE_NAME = "ship_database.db"
    }
}