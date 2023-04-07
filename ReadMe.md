# jetPack 库尝试体验，自己项目体量大，使用改工程练习更方便

##  Navigation  done
##  dataStore 
集成代码生成路径和demo 在ptoto和store 文件夹中
参考链接：https://developer.android.com/codelabs/android-proto-datastore?hl=zh-cn#4

## WorkManager
* 多任务的使用
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

##  wokr的使用场景：
* 自动进行重试，非常适合日志上传时候的场景
* 启动顺序启动任务的场景
* 更新服务配置

* 结合liveData 对结果的观察
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


## LiveData 的高级协程
## Padding3
## 如果还有时间最好 compose也整一些混合开发