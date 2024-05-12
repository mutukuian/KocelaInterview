package com.example.kocelainterview.domain.repository_interface

import com.example.kocelainterview.domain.model.Ship

interface SearchControllerRepository {
    suspend fun searchShip(query:String):List<Ship>
}