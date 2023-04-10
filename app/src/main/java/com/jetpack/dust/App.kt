package com.jetpack.dust

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.work.*
import com.jetpack.dust.work.DelayWorker
import com.jetpack.dust.work.DownloadWorker
import com.jetpack.dust.work.RenameWorkerFactory
import com.jetpack.dust.work.UploadWorker
import dagger.hilt.android.HiltAndroidApp
import java.time.Duration
@HiltAndroidApp
class App : Application(), Configuration.Provider {

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(RenameWorkerFactory())
            .setMinimumLoggingLevel(Log.VERBOSE)
            .build()

    override fun onCreate() {
        super.onCreate()


//        oneTimeWork()
        mutilWork()

    }

    private fun oneTimeWork() {
        val uploadWorkRequest: WorkRequest =
            OneTimeWorkRequestBuilder<UploadWorker>()
                .build()
        WorkManager.getInstance(this)
            .enqueue(uploadWorkRequest)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun repeatWork() {
        val repeatRequest = PeriodicWorkRequestBuilder<UploadWorker>(8, java.util.concurrent.TimeUnit.SECONDS)
                // Additional configuration
            .setInitialDelay(Duration.ofSeconds(5L)) //延迟
                .build()
        WorkManager.getInstance(this)
            .enqueue(repeatRequest)
    }


    private fun mutilWork() {
        val workerList= mutableListOf<OneTimeWorkRequest>()
        val uploadWorkRequest: OneTimeWorkRequest = OneTimeWorkRequestBuilder<UploadWorker>().build()
        val downloadWorkRequest: OneTimeWorkRequest = OneTimeWorkRequestBuilder<DownloadWorker>().build()
        val delayWorkRequest: OneTimeWorkRequest = OneTimeWorkRequestBuilder<DelayWorker>().build()
        workerList.add(uploadWorkRequest)
        workerList.add(downloadWorkRequest )

        WorkManager.getInstance(this)
            .beginWith(workerList)
            .then(delayWorkRequest)
            .enqueue()
        //使用then 时候，delayWork 必须钱前面的任务完成后，才能够进行触发

        //结合liveData 对结果的观察
        WorkManager.getInstance(applicationContext)
            // requestId is the WorkRequest id
            .getWorkInfoByIdLiveData(delayWorkRequest.id)
            .observeForever(Observer { workInfo: WorkInfo? ->
                if (workInfo?.state==WorkInfo.State.SUCCEEDED) {
                    val progress = workInfo.progress
                   val suc= workInfo.outputData.getBoolean("suc",false)
                    Log.d("work", "-------------------mutilWork: suc=$suc")
                    WorkManager.getInstance(this).cancelAllWork()
                }
            })
    }

}