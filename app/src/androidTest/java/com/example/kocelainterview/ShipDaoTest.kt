package com.example.kocelainterview

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.kocelainterview.data.local_data_source.ShipDao
import com.example.kocelainterview.data.local_data_source.ShipDatabase
import com.example.kocelainterview.data.local_data_source.ShipEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShipDaoTest {

    private lateinit var database: ShipDatabase
    private lateinit var  shipDao:ShipDao

    @Before
    fun createDatabase(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShipDatabase::class.java
        ).allowMainThreadQueries().build()
        shipDao = database.shipDao()
    }

    @After
    fun closeDatabase(){
        database.close()
    }

    @Test
    fun insertAndRetrieveSHips() = runTest{
        val ship1 = ShipEntity("ship1", true, "image1.jpg", "Ship 1", 5000, 2020)
        val ship2 = ShipEntity("ship2", false, null, "Ship 2", 6000, 2015)


        shipDao.saveShips(listOf(ship1, ship2))


        val savedShips = shipDao.getShips()


        assertEquals(2, savedShips.size)
        assertEquals(ship1.ship_id, savedShips[0].ship_id)
        assertEquals(ship2.ship_name, savedShips[1].ship_name)

    }

    @Test
    fun insertWithConflict()= runTest{
        val ship1 = ShipEntity("ship1", true, "image1.jpg", "Ship 1", 5000, 2020)
        val ship2 = ShipEntity("ship1", false, null, "Ship 2", 6000, 2015)


        shipDao.saveShips(listOf(ship1))


        shipDao.saveShips(listOf(ship2))


        val savedShips = shipDao.getShips()


        assertEquals(1, savedShips.size)
        assertEquals(ship2.ship_name, savedShips[0].ship_name)

    }
}