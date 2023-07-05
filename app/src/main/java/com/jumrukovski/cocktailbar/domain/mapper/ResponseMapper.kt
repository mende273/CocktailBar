package com.jumrukovski.cocktailbar.domain.mapper

import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.data.model.DrinksResponse
import com.jumrukovski.cocktailbar.data.model.Ingredient
import com.jumrukovski.cocktailbar.data.model.IngredientsResponse
import com.jumrukovski.cocktailbar.data.network.ResponseResult
import retrofit2.Response

fun Response<DrinksResponse<Drink>>.mapDrinksToResponseResult(): ResponseResult<List<Drink>> {
    return try {
        return when (this.isSuccessful) {
            true -> ResponseResult.Success(this.body()?.drinks ?: emptyList())
            false -> ResponseResult.Error(this.errorBody(), this.code())
        }
    } catch (throwable: Throwable) {
        ResponseResult.Exception(throwable)
    }
}

fun Response<DrinksResponse<Drink>>.mapDrinkToResponseResult(): ResponseResult<Drink> {
    return try {
        return when (this.isSuccessful) {
            true -> ResponseResult.Success(this.body()?.drinks?.get(0))
            false -> ResponseResult.Error(this.errorBody(), this.code())
        }
    } catch (throwable: Throwable) {
        ResponseResult.Exception(throwable)
    }
}

fun Response<IngredientsResponse<Ingredient>>.mapIngredientToResponseResult(): ResponseResult<Ingredient> {
    return try {
        return when (this.isSuccessful) {
            true -> ResponseResult.Success(this.body()?.ingredients?.get(0))
            false -> ResponseResult.Error(this.errorBody(), this.code())
        }
    } catch (throwable: Throwable) {
        ResponseResult.Exception(throwable)
    }
}
