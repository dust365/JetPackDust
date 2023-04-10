package com.jetpack.dust.ui.seach.engine

import android.app.Activity
import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.android.scopes.ViewScoped
import javax.inject.Inject
import javax.inject.Singleton

/*
   如何使用context @ActivityContext val context: Context
   @Params @ActivityContext
   @Params @ApplicationContext
   @Singleton 会和ActivityContext 产生冲突，注意修改作用域
 */

/**
 * 作用域的指定
 */
//@ViewScoped
@Singleton
//@FragmentScoped
//@ActivityScoped
class QuickSearchEngine @Inject constructor(@ApplicationContext val context: Context):SearchEngine {
   val TAG= "Hilt"
    override fun start() {
        Log.d(TAG, "start:---QuickSearchEngine")
//        context as Activity
    }

    override fun end() {
        Log.d(TAG, "end:---QuickSearchEngine")
    }
}