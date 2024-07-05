package com.jumrukovski.cocktailbar.data.repository.local

import com.jumrukovski.cocktailbar.data.datasource.LocalDataSource
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.domain.repository.local.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalRepositoryImpl(private val localDataSource: LocalDataSource) : LocalRepository {

    override fun getAllFavoriteDrinks() = localDataSource.drinkDao().all()

    override fun getFavorite(id: Long): Flow<Drink?> = flow {
        emit(
            localDataSource.drinkDao().get(id)
        )
    }

    override fun addFavorite(entity: Drink) {
        localDataSource.drinkDao().add(entity)
    }

    override fun removeFavorite(entity: Drink) {
        localDataSource.drinkDao().delete(entity)
    }
}
