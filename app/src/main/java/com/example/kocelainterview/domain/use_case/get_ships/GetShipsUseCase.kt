package com.example.kocelainterview.domain.use_case.get_ships


import com.example.kocelainterview.common.core.Resource
import com.example.kocelainterview.data.localdatasource.ShipEntity
import com.example.kocelainterview.data.remote.dto.toShip
import com.example.kocelainterview.data.remote.dto.toShipEntity
import com.example.kocelainterview.domain.model.Ship
import com.example.kocelainterview.domain.repository_interface.ShipRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetShipsUseCase @Inject constructor(
    private val repository: ShipRepository,

){
   operator fun invoke(query: String = ""): Flow<Resource<List<ShipEntity>>> = flow {
       try {
           emit(Resource.Loading<List<ShipEntity>>())
           val ships = repository.getShips().map { it.toShipEntity() }
           emit(Resource.Success<List<ShipEntity>>(ships))
       }catch (e:HttpException){
           emit(Resource.Error<List<ShipEntity>>(e.localizedMessage?:"An unexpected error occurred"))
       } catch (e:IOException){
           emit(Resource.Error<List<ShipEntity>>(e.localizedMessage?:"Could not reach the server.Check your internet connection"))

       }
   }
}