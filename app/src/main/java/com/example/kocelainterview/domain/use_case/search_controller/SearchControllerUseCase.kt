package com.example.kocelainterview.domain.use_case.search_controller

import com.example.kocelainterview.common.core.Resource
import com.example.kocelainterview.domain.model.SearchControllerModel
import com.example.kocelainterview.domain.model.Ship
import com.example.kocelainterview.domain.repository_interface.SearchControllerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class SearchControllerUseCase @Inject constructor(
    private val searchControllerRepository: SearchControllerRepository
) {
    operator fun invoke(query:String): Flow<Resource<List<Ship>>> = flow{
        try{

            emit(Resource.Loading<List<Ship>>())
            val searchResults = searchControllerRepository.searchShip(query)
            emit(Resource.Success<List<Ship>>(searchResults))
        }
        catch (e:Exception){
            emit(Resource.Error(e.localizedMessage?: "An unexpected error occurred"))
        }
        catch (e: IOException){
            emit(Resource.Error("Couldn't reach server.Check your connection"))
        }
    }
}