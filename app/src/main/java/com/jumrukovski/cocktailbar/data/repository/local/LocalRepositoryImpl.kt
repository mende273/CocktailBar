package com.jumrukovski.cocktailbar.data.repository.local

import com.jumrukovski.cocktailbar.data.datasource.LocalDataSource
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.domain.repository.local.LocalRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LocalRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : LocalRepository {

    override fun getAllFavoriteDrinks() = localDataSource.drinkDao().all()

    override suspend fun getFavorite(id: Long): Drink? =
        withContext(ioDispatcher) {
            localDataSource.drinkDao().get(id)
        }

    override suspend fun addFavorite(entity: Drink) {
        withContext(ioDispatcher) {
            localDataSource.drinkDao().add(entity)
        }
    }

    override suspend fun removeFavorite(entity: Drink) {
        withContext(ioDispatcher) {
            localDataSource.drinkDao().delete(entity)
        }
    }
}
