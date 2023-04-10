package com.jetpack.dust.ui.seach.engine

import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class SlowSearchEngine @Inject constructor(@ActivityContext val context: Context):SearchEngine {
   val TAG= "Hilt"
    override fun start() {
        Log.d(TAG, "start:---SlowSearchEngine")
    }

    override fun end() {
        Log.d(TAG, "end:---SlowSearchEngine")
    }
}