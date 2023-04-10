package com.jetpack.dust.ui.seach.engine

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Qualifier

/**
 * @author dust
 * @Date 2023/4/10 通过这个抽象的来作为引擎的提供者
 * @function
 */
@Module
@InstallIn(FragmentComponent::class) //指定作用域
abstract  class EnginModule {

    // 没有注解时候直接通过 构造来 指定具体的实例
    @Binds
    abstract fun bindEngine(engine: QuickSearchEngine): SearchEngine



    //返回类型相同时候，添加注解
//    @BindQuickEngin
//    @Binds
//    abstract fun bindQuickEngine(engine: QuickSearchEngine): SearchEngine
//
//
//    @BindSlowEngin
//    @Binds
//    abstract fun bindSlowEngine(quickEngine: SlowSearchEngine): SearchEngine

}

