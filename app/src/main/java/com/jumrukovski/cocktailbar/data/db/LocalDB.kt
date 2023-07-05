package com.jumrukovski.cocktailbar.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jumrukovski.cocktailbar.data.model.Drink

@Database(entities = [Drink::class], version = 1)
abstract class LocalDB : RoomDatabase() {

    abstract fun drinkDao(): DrinkDao
}
