package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.data.repository.ApiRepository
import com.jumrukovski.cocktailbar.domain.mapper.mapDrinksToResponseResult
import kotlinx.coroutines.flow.flow

class GetFilteredDrinksUseCase(private val apiRepository: ApiRepository) {

    fun invoke(param: String, value: String) = flow {
        emit(apiRepository.getFilteredDrinks(param, value).mapDrinksToResponseResult())
    }
}