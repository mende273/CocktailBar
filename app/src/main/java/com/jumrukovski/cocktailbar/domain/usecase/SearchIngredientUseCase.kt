package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.base.BaseUseCase
import com.jumrukovski.cocktailbar.domain.mapper.mapIngredientToResponseData
import com.jumrukovski.cocktailbar.data.model.Ingredient
import com.jumrukovski.cocktailbar.data.repository.ApiRepository
import kotlinx.coroutines.flow.flow

class SearchIngredientUseCase(private val apiRepository: ApiRepository) :
    BaseUseCase<Ingredient>() {

    override fun invoke(vararg values: String) = flow {
        val response = apiRepository.searchIngredient(values[0])

        emit(response.mapIngredientToResponseData())
    }
}