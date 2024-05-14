package com.example.kocelainterview.domain.use_case.get_ships


import com.example.kocelainterview.common.core.Resource
import com.example.kocelainterview.data.remote.dto.toShip
import com.example.kocelainterview.domain.model.Ship
import com.example.kocelainterview.domain.repository_interface.ShipRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetShipsUseCase @Inject constructor(
    private val repository: ShipRepository,

){
   operator fun invoke(query: String = ""): Flow<Resource<List<Ship>>> = flow {
       try {
           emit(Resource.Loading<List<Ship>>())
           val allShips = repository.getShips()
           val filteredShips = if(query.isEmpty()){
               allShips.map { it.toShip() }
           }else{
               allShips.map { it.toShip() }.filter { ship->
                   ship.ship_name.contains(query,ignoreCase = true)
               }
           }
           emit(Resource.Success<List<Ship>>(filteredShips))
       }catch (e:HttpException){
           emit(Resource.Error<List<Ship>>(e.localizedMessage?:"An unexpected error occurred"))
       } catch (e:IOException){
           emit(Resource.Error<List<Ship>>(e.localizedMessage?:"Could not reach the server.Check your internet connection"))

       }
   }
}