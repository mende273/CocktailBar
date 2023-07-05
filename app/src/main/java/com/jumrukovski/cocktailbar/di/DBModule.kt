package com.jumrukovski.cocktailbar.di

import android.content.Context
import androidx.room.Room
import com.jumrukovski.cocktailbar.data.db.LocalDB
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single { provideDatabase(androidContext(), provideDatabaseName()) }
}

fun provideDatabaseName(): String = "CocktailBarDB"

fun provideDatabase(context: Context, databaseName: String): LocalDB {
    return Room.databaseBuilder(context, LocalDB::class.java, databaseName).build()
}
