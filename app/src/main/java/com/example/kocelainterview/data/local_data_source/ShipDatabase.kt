package com.example.kocelainterview.data.local_data_source

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ShipEntity::class], version = 2, exportSchema = false)
abstract class ShipDatabase:RoomDatabase (){
    abstract fun shipDao():ShipDao
}