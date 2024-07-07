package com.jumrukovski.cocktailbar.data.repository.remote

import com.jumrukovski.cocktailbar.data.datasource.RemoteDataSource
import com.jumrukovski.cocktailbar.domain.repository.remote.RemoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RemoteRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : RemoteRepository {

    override suspend fun getCocktailsByFirstLetter(firstLetter: String) =
        withContext(ioDispatcher) {
            runCatching {
                remoteDataSource
                    .getCocktailsByFirstLetter(firstLetter).drinks
            }
        }

    override suspend fun searchDrinksByName(name: String) =
        withContext(ioDispatcher) {
            runCatching {
                remoteDataSource
                    .searchDrinksByName(name).drinks
            }
        }

    override suspend fun getFilterListForOption(param: String, value: String) =
        withContext(ioDispatcher) {
            runCatching {
                remoteDataSource
                    .getFilterListForOption(mapOf(param to value))
                    .drinks
            }
        }

    override suspend fun getFilteredDrinks(param: String, value: String) =
        withContext(ioDispatcher) {
            runCatching {
                remoteDataSource
                    .getFilteredDrinks(mapOf(param to value)).drinks
            }
        }

    override suspend fun getDrinkDetails(id: Long) =
        withContext(ioDispatcher) {
            runCatching {
                remoteDataSource
                    .getDrinkDetails(id)
                    .drinks
                    .first()
            }
        }

    override suspend fun searchIngredient(name: String) =
        withContext(ioDispatcher) {
            runCatching {
                remoteDataSource
                    .searchIngredient(name)
                    .ingredients
                    .first()
            }
        }
}
