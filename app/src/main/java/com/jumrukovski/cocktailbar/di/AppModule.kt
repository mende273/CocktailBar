package com.jumrukovski.cocktailbar.di

import org.koin.dsl.module

val appModule = module {
    includes(
        networkModule,
        remoteDataSourceModule,
        remoteRepositoryModule,
        viewModelModule,
        adapterModule,
        localDataSourceModule,
        localRepositoryModule
    )
}
