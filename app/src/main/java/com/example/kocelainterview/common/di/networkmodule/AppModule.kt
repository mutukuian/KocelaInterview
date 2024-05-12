package com.example.kocelainterview.common.di.networkmodule

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kocelainterview.common.core.Constants.BASE_URL
import com.example.kocelainterview.data.localdatasource.ShipDao
import com.example.kocelainterview.data.localdatasource.ShipDatabase
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


//    @Provides
//    @Singleton
//    abstract fun provideShipDatabase(factory: RoomDatabase.Callback): ShipDatabase


    @Provides
    @Singleton
    fun provideDao(roomDatabase: ShipDatabase):ShipDao{
        return roomDatabase.shipDao
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(context: Context): RoomDatabase {
        return Room.databaseBuilder(context, ShipDatabase::class.java, ShipDatabase.DATABASE_NAME)
            .build()
    }

}