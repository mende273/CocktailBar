package com.jumrukovski.cocktailbar.domain.mapper

import com.jumrukovski.cocktailbar.data.model.*
import retrofit2.Response

fun Response<DrinksResponse<Drink>>.mapDrinksToResponseData(): ResponseData<List<Drink>> {
    return ResponseData(
        this.body()?.drinks ?: listOf(),
        this.isSuccessful,
        this.code()
    )
}

fun Response<DrinksResponse<Drink>>.mapDrinkToResponseData(): ResponseData<Drink> {
    return ResponseData(
        this.body()?.drinks?.get(0),
        this.isSuccessful,
        this.code()
    )
}

fun Response<IngredientsResponse<Ingredient>>.mapIngredientToResponseData(): ResponseData<Ingredient>{
    return ResponseData(
        this.body()?.ingredients?.get(0),
        this.isSuccessful,
        this.code()
    )
}