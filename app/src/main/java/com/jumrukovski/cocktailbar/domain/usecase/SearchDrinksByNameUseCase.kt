package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.data.repository.ApiRepository
import com.jumrukovski.cocktailbar.domain.mapper.mapDrinksToResponseResult
import kotlinx.coroutines.flow.flow

class SearchDrinksByNameUseCase(private val apiRepository: ApiRepository) {

    fun invoke(name: String) = flow {
        emit(apiRepository.searchDrinksByName(name).mapDrinksToResponseResult())
    }
}