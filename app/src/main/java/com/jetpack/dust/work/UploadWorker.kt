package com.jetpack.dust.work

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class UploadWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {


    companion object{
        const val TAG="work"
        var failureCount=0
    }

    override fun doWork(): Result {
        Log.d(TAG, "doWork: 开始睡5秒$failureCount")
        Thread.sleep(3000)
        Log.d(TAG, "doWork: 睡眠结束")

        if (failureCount>=2){
            return Result.success()
        }else {
            failureCount++
            return Result.retry()
        }
    }
}