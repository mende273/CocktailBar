package com.jumrukovski.cocktailbar.di

import org.koin.dsl.module

val appModule = module {
    includes(
        networkModule,
        apiRepositoryModule,
        viewModelModule,
        adapterModule,
        dbModule,
        localRepositoryModule
    )
}
