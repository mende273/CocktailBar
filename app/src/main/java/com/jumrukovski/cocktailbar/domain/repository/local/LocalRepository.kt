package com.jumrukovski.cocktailbar.domain.repository.local

import androidx.lifecycle.LiveData
import com.jumrukovski.cocktailbar.data.model.Drink

interface LocalRepository {
    fun getAllFavoriteDrinks(): LiveData<List<Drink>>

    suspend fun getFavorite(id: Long): Drink?

    suspend fun addFavorite(entity: Drink)

    suspend fun removeFavorite(entity: Drink)
}
