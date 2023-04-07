package com.jetpack.dust.work

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters

class DelayWorker(var context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    companion object{
        const val TAG="work"
    }

    override fun doWork(): Result {
        Log.d(TAG, "doWork: 开始---DelayWorker")
        Thread.sleep(2000)
        Log.d(TAG, "doWork: 完成---DelayWorker")
//        WorkManager.getInstance(context).cancelAllWork()

        val data=  Data.Builder()
          .putBoolean("suc",true)
          .build()
        return  Result.success(data)
    }
}