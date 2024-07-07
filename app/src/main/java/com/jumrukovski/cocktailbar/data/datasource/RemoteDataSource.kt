package com.jumrukovski.cocktailbar.data.datasource

import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.data.model.DrinksResponse
import com.jumrukovski.cocktailbar.data.model.Ingredient
import com.jumrukovski.cocktailbar.data.model.IngredientsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface RemoteDataSource {

    companion object {
        const val ENDPOINT = "https://www.thecocktaildb.com/api/json/v1/1/"
        const val INGREDIENT_IMAGES_ENDPOINT = "https://www.thecocktaildb.com/images/ingredients/"
    }

    @GET("search.php")
    suspend fun getCocktailsByFirstLetter(
        @Query("f") firstLetter: String
    ): DrinksResponse<Drink>

    @GET("list.php")
    suspend fun getFilterListForOption(
        @QueryMap options: Map<String, String>
    ): DrinksResponse<Drink>

    @GET("search.php")
    suspend fun searchDrinksByName(
        @Query("s") name: String
    ): DrinksResponse<Drink>

    @GET("filter.php")
    suspend fun getFilteredDrinks(
        @QueryMap options: Map<String, String>
    ): DrinksResponse<Drink>

    @GET("lookup.php")
    suspend fun getDrinkDetails(
        @Query("i") id: Long
    ): DrinksResponse<Drink>

    @GET("search.php")
    suspend fun searchIngredient(
        @Query("i") name: String
    ): IngredientsResponse<Ingredient>
}
