package com.jumrukovski.cocktailbar.data.repository

import com.jumrukovski.cocktailbar.data.network.ApiService

class ApiRepository(private val apiService: ApiService) {

    suspend fun getCocktailsByFirstLetterAsync(firstLetter: String) =
        apiService.getCocktailsByFirstLetterAsync(firstLetter)

    suspend fun searchDrinksByNameAsync(name: String) = apiService.searchDrinksByNameAsync(name)

    suspend fun getFilterListForOptionAsync(param: String, value: String) =
        apiService.getFilterListForOptionAsync(mapOf(param to value))

    suspend fun getFilteredDrinksAsync(param: String, value: String) =
        apiService.getFilteredDrinksAsync(mapOf(param to value))

    suspend fun getDrinkDetailsAsync(id: Long) = apiService.getDrinkDetailsAsync(id)

    suspend fun searchIngredientAsync(name: String) = apiService.searchIngredientAsync(name)
}
