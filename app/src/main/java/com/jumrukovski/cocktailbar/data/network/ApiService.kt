package com.jumrukovski.cocktailbar.data.network

import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.data.model.DrinksResponse
import com.jumrukovski.cocktailbar.data.model.Ingredient
import com.jumrukovski.cocktailbar.data.model.IngredientsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {

    companion object {
        const val ENDPOINT = "https://www.thecocktaildb.com/api/json/v1/1/"
        const val INGREDIENT_IMAGES_ENDPOINT = "https://www.thecocktaildb.com/images/ingredients/"
    }

    @GET("search.php")
    suspend fun getCocktailsByFirstLetterAsync(@Query("f") firstLetter: String): Response<DrinksResponse<Drink>>

    @GET("list.php")
    suspend fun getFilterListForOptionAsync(@QueryMap options: Map<String, String>): Response<DrinksResponse<Drink>>

    @GET("search.php")
    suspend fun searchDrinksByNameAsync(@Query("s") name: String): Response<DrinksResponse<Drink>>

    @GET("filter.php")
    suspend fun getFilteredDrinksAsync(@QueryMap options: Map<String, String>): Response<DrinksResponse<Drink>>

    @GET("lookup.php")
    suspend fun getDrinkDetailsAsync(@Query("i") id: Long): Response<DrinksResponse<Drink>>

    @GET("search.php")
    suspend fun searchIngredientAsync(@Query("i") name: String): Response<IngredientsResponse<Ingredient>>
}
