package com.example.kocelainterview.domain.use_case.get_ship

import com.example.kocelainterview.common.core.Resource
import com.example.kocelainterview.data.remote.dto.toShipDetail
import com.example.kocelainterview.domain.model.ShipDetail
import com.example.kocelainterview.domain.repository_interface.ShipRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetShipUseCase @Inject constructor(
    private val repository: ShipRepository
) {
    operator fun invoke(shipId:String): Flow<Resource<ShipDetail>> = flow {
        try {
            emit(Resource.Loading<ShipDetail>())
            val ship = repository.getShipById(shipId).toShipDetail()
            emit(Resource.Success<ShipDetail>(ship))
        }catch (e: HttpException){
            emit(Resource.Error<ShipDetail>(e.localizedMessage?:"An unexpected error occurred"))
        } catch (e: IOException){
            emit(Resource.Error<ShipDetail>(e.localizedMessage?:"Could not reach the server.Check your internet connection"))

        }
    }
}