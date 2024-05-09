package com.example.kocelainterview.domain.repository_interface

import com.example.kocelainterview.data.remote.dto.ShipDetailDto
import com.example.kocelainterview.data.remote.dto.ShipDto

interface ShipRepository {

    suspend fun getShips():List<ShipDto>

    suspend fun getShipById(shipId:String):ShipDetailDto
}