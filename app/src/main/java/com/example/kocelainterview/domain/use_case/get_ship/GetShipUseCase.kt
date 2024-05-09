package com.example.kocelainterview.domain.use_case.get_ship

import com.example.kocelainterview.common.core.Resource
import com.example.kocelainterview.data.remote.dto.toShipDetail
import com.example.kocelainterview.domain.model.ShipDetail
import com.example.kocelainterview.domain.repository_interface.ShipRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetShipUseCase(
    private val repository: ShipRepository
) {
    operator fun invoke(shipId:String): Flow<Resource<ShipDetail>> = flow {
        try {
            emit(Resource.Loading())
            val ship = repository.getShipById(shipId).toShipDetail()
            emit(Resource.Success(ship))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage?:"An unexpected error occurred"))
        } catch (e: IOException){
            emit(Resource.Error(e.localizedMessage?:"Could not reach the server.Check your internet connection"))

        }
    }
}