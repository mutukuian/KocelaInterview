package com.example.kocelainterview.common.di.networkmodule


import com.example.kocelainterview.common.core.Constants.BASE_URL
import com.example.kocelainterview.data.remote.api_service.ShipsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module

object AppModule {

    @Provides
    @Singleton
    fun providesShipApi():ShipsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ShipsApi::class.java)

    }

}