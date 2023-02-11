package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.data.repository.ApiRepository
import com.jumrukovski.cocktailbar.domain.mapper.mapIngredientToResponseResult
import kotlinx.coroutines.flow.flow

class SearchIngredientUseCase(private val apiRepository: ApiRepository) {

    fun invoke(name: String) = flow {
        emit(apiRepository.searchIngredient(name).mapIngredientToResponseResult())
    }
}