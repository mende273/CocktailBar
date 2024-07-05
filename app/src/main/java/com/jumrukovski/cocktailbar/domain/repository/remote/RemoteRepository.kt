package com.jumrukovski.cocktailbar.domain.repository.remote

import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.data.model.DrinksResponse
import com.jumrukovski.cocktailbar.data.model.Ingredient
import com.jumrukovski.cocktailbar.data.model.IngredientsResponse
import retrofit2.Response

interface RemoteRepository {
    suspend fun getCocktailsByFirstLetterAsync(firstLetter: String): Response<DrinksResponse<Drink>>

    suspend fun searchDrinksByNameAsync(name: String): Response<DrinksResponse<Drink>>

    suspend fun getFilterListForOptionAsync(
        param: String,
        value: String
    ): Response<DrinksResponse<Drink>>

    suspend fun getFilteredDrinksAsync(
        param: String,
        value: String
    ): Response<DrinksResponse<Drink>>

    suspend fun getDrinkDetailsAsync(id: Long): Response<DrinksResponse<Drink>>

    suspend fun searchIngredientAsync(name: String): Response<IngredientsResponse<Ingredient>>
}
