package com.jumrukovski.cocktailbar.di

import com.jumrukovski.cocktailbar.data.datasource.RemoteDataSource
import org.koin.dsl.module
import retrofit2.Retrofit

val remoteDataSourceModule = module {
    single { provideRemoteDataSource(get()) }
}

fun provideRemoteDataSource(retrofit: Retrofit): RemoteDataSource {
    return retrofit.create(RemoteDataSource::class.java)
}
