package com.example.kocelainterview.domain.use_case.get_ships


import com.example.kocelainterview.common.core.Resource
import com.example.kocelainterview.data.remote.dto.toShip
import com.example.kocelainterview.domain.model.Ship
import com.example.kocelainterview.domain.repository_interface.ShipRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetShipsUseCase @Inject constructor(
    private val repository: ShipRepository
){
   operator fun invoke(): Flow<Resource<List<Ship>>> = flow {
       try {
           emit(Resource.Loading())
           val ships = repository.getShips().map { it.toShip() }
           emit(Resource.Success(ships))
       }catch (e:HttpException){
           emit(Resource.Error(e.localizedMessage?:"An unexpected error occurred"))
       } catch (e:IOException){
           emit(Resource.Error(e.localizedMessage?:"Could not reach the server check your internet connection"))

       }
   }
}