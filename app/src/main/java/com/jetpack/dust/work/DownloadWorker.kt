package com.jetpack.dust.work

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class DownloadWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    companion object{
        const val TAG="work"
    }

    override fun doWork(): Result {
        Log.d(TAG, "doWork: 开始下载")
        Thread.sleep(5000)
        Log.d(TAG, "doWork: 下载完成")
         return  Result.success()
    }
}