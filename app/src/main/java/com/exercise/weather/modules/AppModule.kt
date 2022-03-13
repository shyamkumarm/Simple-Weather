package com.exercise.weather.modules

import com.exercise.weather.utils.SystemUtils
import org.koin.dsl.module

private val appModule = module {
    single { SystemUtils(get()) }

}




val appModules = listOf(appModule)