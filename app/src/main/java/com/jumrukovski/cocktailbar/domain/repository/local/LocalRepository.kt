package com.jumrukovski.cocktailbar.domain.repository.local

import androidx.lifecycle.LiveData
import com.jumrukovski.cocktailbar.data.model.Drink
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    fun getAllFavoriteDrinks(): LiveData<List<Drink>>

    fun getFavorite(id: Long): Flow<Drink?>

    fun addFavorite(entity: Drink)

    fun removeFavorite(entity: Drink)
}
