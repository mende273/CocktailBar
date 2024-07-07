package com.jumrukovski.cocktailbar.domain.repository.remote

import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.data.model.Ingredient

interface RemoteRepository {
    suspend fun getCocktailsByFirstLetter(firstLetter: String): Result<List<Drink>>

    suspend fun searchDrinksByName(name: String): Result<List<Drink>>

    suspend fun getFilterListForOption(param: String, value: String): Result<List<Drink>>

    suspend fun getFilteredDrinks(param: String, value: String): Result<List<Drink>>

    suspend fun getDrinkDetails(id: Long): Result<Drink>

    suspend fun searchIngredient(name: String): Result<Ingredient>
}
