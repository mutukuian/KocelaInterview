package com.example.kocelainterview.common.di.repositorymodule

import com.example.kocelainterview.data.local_data_source.ShipDao
import com.example.kocelainterview.data.remote.api_service.ShipsApi
import com.example.kocelainterview.data.local_data_source.ShipDatabase
import com.example.kocelainterview.data.repository.SearchControllerRepositoryImpl
import com.example.kocelainterview.data.repository.ShipRepositoryImpl
import com.example.kocelainterview.domain.repository_interface.SearchControllerRepository
import com.example.kocelainterview.domain.repository_interface.ShipRepository
import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun providesShipRepository(api:ShipsApi,shipDao: ShipDao):ShipRepository{
        return ShipRepositoryImpl(api,shipDao)
    }

    @Provides
    @Singleton
    fun provideShipDao(database: ShipDatabase): ShipDao {
        return database.shipDao()
    }

    @Provides
    @Singleton
    fun provideShipDatabase(@ApplicationContext context: Context): ShipDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ShipDatabase::class.java,
            "ship_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun providesSearchControllerRepository(api: ShipsApi):SearchControllerRepository{
        return SearchControllerRepositoryImpl(api)
    }

}
