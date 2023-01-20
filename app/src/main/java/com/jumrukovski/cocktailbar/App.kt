package com.jumrukovski.cocktailbar

import android.app.Application
import com.jumrukovski.cocktailbar.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    apiRepositoryModule,
                    viewModelModule,
                    adapterModule,
                    dbModule,
                    localRepositoryModule
                )
            )
        }
    }
}