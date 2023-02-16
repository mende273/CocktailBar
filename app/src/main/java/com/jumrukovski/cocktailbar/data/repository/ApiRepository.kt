package com.jumrukovski.cocktailbar.data.repository

import com.jumrukovski.cocktailbar.data.network.ApiService

class ApiRepository(private val apiService: ApiService) {

    suspend fun getCocktailsByFirstLetterAsync(firstLetter: String) =
        apiService.getCocktailsByFirstLetterAsync(firstLetter)

    suspend fun searchDrinksByName(name: String) = apiService.searchDrinksByName(name)

    suspend fun getFilterListFor(param: String, value: String) =
        apiService.getFilterListFor(mapOf(param to value))

    suspend fun getFilteredDrinks(param: String, value: String) =
        apiService.getFilteredDrinks(mapOf(param to value))

    suspend fun getDrinkDetails(id: Long) = apiService.getDrinkDetails(id)

    suspend fun searchIngredient(name: String) = apiService.searchIngredient(name)
}