package com.emad.dummyproducts.domain.di

import com.emad.dummyproducts.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    single { initMainRepository(get()) }
    viewModel { MainViewModel(get()) }
}