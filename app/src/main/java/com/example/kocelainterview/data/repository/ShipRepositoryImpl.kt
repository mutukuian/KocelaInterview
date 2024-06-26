package com.example.kocelainterview.data.repository

import com.example.kocelainterview.data.local_data_source.ShipDao
import com.example.kocelainterview.data.local_data_source.toShipDto
import com.example.kocelainterview.data.remote.api_service.ShipsApi
import com.example.kocelainterview.data.remote.dto.ShipDetailDto
import com.example.kocelainterview.data.remote.dto.ShipDto
import com.example.kocelainterview.data.remote.dto.toEntity
import com.example.kocelainterview.domain.repository_interface.ShipRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShipRepositoryImpl @Inject constructor(
    private val api: ShipsApi,
    private val shipDao: ShipDao
) : ShipRepository {

    override suspend fun getShips(): List<ShipDto> = withContext(Dispatchers.IO) {
        // Check Room database first
        val cachedShips = shipDao.getShips()
        if (cachedShips.isNotEmpty()) {
            return@withContext cachedShips.map { it.toShipDto() }
        }

        // Fetch from API if not found in cache
        val ships = api.getShips()

        // Save to Room database
        shipDao.saveShips(ships.map { it.toEntity() })

        return@withContext ships
    }

    override suspend fun getShipById(shipId: String): ShipDetailDto= withContext(Dispatchers.IO) {
        return@withContext api.getShipById(shipId)
    }
}
