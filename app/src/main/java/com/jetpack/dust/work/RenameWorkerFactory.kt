package com.jetpack.dust.work

import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import kotlin.math.log

class RenameWorkerFactory : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        Log.d("work", "workerClassName: $workerClassName")
        return when(workerClassName) {
            "RxCleanupWorker" -> CleanupWorker(appContext, workerParameters)
            else -> null
        }
    }

}
