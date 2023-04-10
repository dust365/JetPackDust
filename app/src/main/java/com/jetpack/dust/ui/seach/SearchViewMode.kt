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
//可以通过构造函数 注入网络的resopse
//结合private val fooViewModel: FooViewModel by viewModels()  使用
@HiltViewModel
class SearchViewMode @Inject constructor() :ViewModel() {

    private  val TAG = "Hilt"


   fun goToSearch(key:String){
       viewModelScope.launch {
           withContext(Dispatchers.IO){
               Log.d(TAG, "goToSearch: key=$key")
           }

       }
   }

}