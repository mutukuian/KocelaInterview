package com.example.kocelainterview.data.repository

import com.example.kocelainterview.data.remote.api_service.ShipsApi
import com.example.kocelainterview.data.remote.dto.ShipDetailDto
import com.example.kocelainterview.data.remote.dto.ShipDto
import com.example.kocelainterview.domain.repository_interface.ShipRepository
import javax.inject.Inject

class ShipRepositoryImpl @Inject constructor(
    private val api:ShipsApi
) :ShipRepository{
    override suspend fun getShips(): List<ShipDto> {
       return api.getShips()
    }

    override suspend fun getShipById(shipId: String): ShipDetailDto {
        return api.getShipById(shipId)
    }
}