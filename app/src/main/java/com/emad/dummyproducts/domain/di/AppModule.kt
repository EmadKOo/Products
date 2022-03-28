package com.emad.dummyproducts.domain.di

import org.koin.dsl.module

val AppModule = module {
    single { initMainRepository(get()) }
}