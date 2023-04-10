package com.jetpack.dust.ui.seach

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


//必须添加构造
//携带其他类型的构造参数
class SearchMode @Inject constructor(var hotKey: HotKey) {

    private  val TAG = "Hilt"


   fun goToSearch(key:String){
//       viewModelScope.launch {
//           withContext(Dispatchers.IO){
               Log.d(TAG, "goToSearch: key=$key  hotId=${hotKey.id}")
//           }
//
//       }
   }

}