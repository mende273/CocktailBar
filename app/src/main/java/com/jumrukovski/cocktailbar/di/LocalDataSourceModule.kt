package com.jumrukovski.cocktailbar.di

import android.content.Context
import androidx.room.Room
import com.jumrukovski.cocktailbar.data.datasource.LocalDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localDataSourceModule = module {
    single { provideLocalDataSource(androidContext()) }
}

fun provideLocalDataSource(context: Context): LocalDataSource {
    return Room.databaseBuilder(context, LocalDataSource::class.java, "CocktailBarDB").build()
}
