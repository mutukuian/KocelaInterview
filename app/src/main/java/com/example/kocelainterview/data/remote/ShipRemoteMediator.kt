package com.example.kocelainterview.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.kocelainterview.data.localdatasource.ShipDatabase
import com.example.kocelainterview.data.localdatasource.ShipsEntity
import com.example.kocelainterview.data.remote.api_service.ShipsApi
import com.example.kocelainterview.data.remote.dto.toShipEntity
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class ShipRemoteMediator(
    private val shipDb:ShipDatabase,
    private val shipApi:ShipsApi,

) :RemoteMediator<Int,ShipsEntity>(){

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ShipsEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType){
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastitem = state.lastItemOrNull()
                    if (lastitem == null){
                        1
                    }else{
                        (lastitem.ship_id.toInt() / state.config.pageSize) + 1
                    }
                }
            }

            val ships = shipApi.getShips(
                page = loadKey,
                pageCount = state.config.pageSize
            )

            shipDb.withTransaction {
                if (loadType == LoadType.REFRESH){
                    shipDb.dao.clearAll()
                }
                val shipEntities = ships.map { it.toShipEntity() }
                shipDb.dao.upsertAll(shipEntities)
            }
            MediatorResult.Success(
                endOfPaginationReached = ships.isEmpty()
            )
        }catch (e:IOException){
            MediatorResult.Error(e)
        }catch (e:HttpException){
            MediatorResult.Error(e)
        }
    }
}