package com.jumrukovski.cocktailbar.data.repository.remote

import com.jumrukovski.cocktailbar.data.datasource.RemoteDataSource
import com.jumrukovski.cocktailbar.domain.repository.remote.RemoteRepository

class RemoteRepositoryImpl(private val remoteDataSource: RemoteDataSource) : RemoteRepository {

    override suspend fun getCocktailsByFirstLetterAsync(firstLetter: String) =
        remoteDataSource.getCocktailsByFirstLetterAsync(firstLetter)

    override suspend fun searchDrinksByNameAsync(name: String) =
        remoteDataSource.searchDrinksByNameAsync(
            name
        )

    override suspend fun getFilterListForOptionAsync(param: String, value: String) =
        remoteDataSource.getFilterListForOptionAsync(mapOf(param to value))

    override suspend fun getFilteredDrinksAsync(param: String, value: String) =
        remoteDataSource.getFilteredDrinksAsync(mapOf(param to value))

    override suspend fun getDrinkDetailsAsync(id: Long) = remoteDataSource.getDrinkDetailsAsync(id)

    override suspend fun searchIngredientAsync(name: String) =
        remoteDataSource.searchIngredientAsync(name)
}
