package com.jumrukovski.cocktailbar.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jumrukovski.cocktailbar.data.model.Drink

@Dao
interface DrinkDao {

    @Query("SELECT * FROM Drink ORDER BY strDrink")
    fun all(): LiveData<List<Drink>>

    @Query("SELECT * FROM Drink WHERE idDrink=:id")
    fun get(id: Long): Drink

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(entity: Drink)

    @Delete
    fun delete(entity: Drink)
}
