package com.example.kocelainterview.common.di.repositorymodule

import com.example.kocelainterview.data.remote.api_service.ShipsApi
import com.example.kocelainterview.data.repository.ShipRepositoryImpl
import com.example.kocelainterview.domain.repository_interface.ShipRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun providesShipRepository(api:ShipsApi):ShipRepository{
        return ShipRepositoryImpl(api)
    }

}