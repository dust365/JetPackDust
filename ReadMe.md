# jetPack 库尝试体验，自己项目体量大，使用改工程练习更方便

##  Navigation  done

* 核心在于对action 的指定，在activity 、fragment 、view中使用findNavController()进行跳转

* 带参数的跳转
  val bundle=Bundle()
  bundle.putString("hotKey","啦啦啦德玛西亚")
  findNavController().navigate(R.id.action_navigation_home_to_navigation_search,bundle)

* navigation 文件编写样例子
    <fragment
        android:id="@+id/navigation_search"
        android:name="com.jetpack.dust.ui.seach.SearchFragment"
        android:label="搜索"
        tools:layout="@layout/fragment_person" >


        <action android:id="@+id/action_navigation_search_to_navigation_person"
            app:destination="@id/navigation_notifications"
            app:enterAnim="@anim/nav_default_enter_anim"
            app:exitAnim="@anim/nav_default_exit_anim"
            app:popEnterAnim="@anim/nav_default_pop_enter_anim"
            app:popExitAnim="@anim/nav_default_pop_exit_anim"
            app:popUpTo="@+id/navigation_notifications"
            app:popUpToInclusive="true"/>
    </fragment>




##  dataStore 
* 集成代码生成路径和demo
* 具体测试代码在ptoto和store 文件夹中StoreUtils的sample
* 参考链接：https://developer.android.com/codelabs/android-proto-datastore?hl=zh-cn#4

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
* 文件上传可以回调进度，参考如下链接https://developer.android.com/topic/libraries/architecture/workmanager/how-to/intermediate-progress?hl=zh-cn
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



## Room
## Padding3
## LiveData 的高级协程
## 如果还有时间最好 compose也整一些混合开发