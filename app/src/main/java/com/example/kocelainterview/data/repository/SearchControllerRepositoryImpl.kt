package com.example.kocelainterview.data.repository

import com.example.kocelainterview.data.remote.api_service.ShipsApi
import com.example.kocelainterview.data.remote.dto.toShip

import com.example.kocelainterview.domain.model.Ship
import com.example.kocelainterview.domain.repository_interface.SearchControllerRepository
import javax.inject.Inject

class SearchControllerRepositoryImpl @Inject constructor(
    private val api:ShipsApi
):SearchControllerRepository {
    override suspend fun searchShip(query: String): List<Ship> {
        // Fetch all ships from the API
        val allShipsDto = api.getShips() // Replace with the endpoint to get all ships

        // Filter ships based on the search query (case-insensitive)
        val filteredShips = allShipsDto.filter { it.ship_name?.lowercase()?.contains(query.lowercase()) ?: false }

        // Map the filtered DTOs to Ship domain models
        return filteredShips.map { it.toShip() }
    }
}