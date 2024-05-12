package com.example.kocelainterview.data.remote.api_service

import com.example.kocelainterview.data.remote.dto.ShipDetailDto
import com.example.kocelainterview.data.remote.dto.ShipDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShipsApi {

    //'https://api.spacexdata.com/v3/ships
    @GET("/v3/ships")
    suspend fun getShips():List<ShipDto>

    //'https://api.spacexdata.com/v3/ships/MRSTEVEN'
    @GET("/v3/ships/{shipId}")
    suspend fun getShipById(@Path("shipId") shipId:String):ShipDetailDto
}