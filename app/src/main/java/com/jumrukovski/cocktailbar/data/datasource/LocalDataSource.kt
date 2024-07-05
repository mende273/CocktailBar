package com.jumrukovski.cocktailbar.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jumrukovski.cocktailbar.data.db.DrinkDao
import com.jumrukovski.cocktailbar.data.model.Drink

@Database(entities = [Drink::class], version = 1)
abstract class LocalDataSource : RoomDatabase() {

    abstract fun drinkDao(): DrinkDao
}
