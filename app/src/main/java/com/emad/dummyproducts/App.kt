package com.emad.dummyproducts

import android.app.Application
import com.emad.dummyproducts.domain.di.AppModule
import com.emad.dummyproducts.domain.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(AppModule, NetworkModule)
        }
    }
}