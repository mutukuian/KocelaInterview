package com.example.kocelainterview.data.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.kocelainterview.domain.repository_interface.ShipRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


@HiltWorker
class ShipSyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams:WorkerParameters,
    private val shipRepository: ShipRepository
):CoroutineWorker(appContext,workerParams) {
    override suspend fun doWork(): Result {
        return try {
            shipRepository.getShips()
            Result.success()
        }catch (e:Exception){
            Result.failure()
        }
    }
}