package com.jetpack.dust.ui.seach.engine

import javax.inject.Qualifier


//限定符  限制同种类型

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BindQuickEngin


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BindSlowEngin
