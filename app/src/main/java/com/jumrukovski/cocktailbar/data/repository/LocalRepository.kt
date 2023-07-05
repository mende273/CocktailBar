package com.jumrukovski.cocktailbar.data.repository

import com.jumrukovski.cocktailbar.data.db.LocalDB
import com.jumrukovski.cocktailbar.data.model.Drink
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalRepository(private val localDB: LocalDB) {

    fun getAllFavoriteDrinks() = localDB.drinkDao().all()

    fun getFavorite(id: Long): Flow<Drink?> = flow { emit(localDB.drinkDao().get(id)) }

    fun addFavorite(entity: Drink) {
        localDB.drinkDao().add(entity)
    }

    fun removeFavorite(entity: Drink) {
        localDB.drinkDao().delete(entity)
    }
}
