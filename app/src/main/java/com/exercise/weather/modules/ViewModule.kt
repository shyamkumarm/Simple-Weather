package com.exercise.weather.modules


import com.exercise.weather.viewmodels.ForeCastViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module





val requestViewModel = module  {
    viewModel{ ForeCastViewModel(get()) }

}

val viewModules = listOf(requestViewModel)

