package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.data.repository.ApiRepository
import com.jumrukovski.cocktailbar.domain.mapper.mapDrinksToResponseResult
import kotlinx.coroutines.flow.flow

class GetCocktailsByFirstLetterUseCase(private val apiRepository: ApiRepository) {

    fun invoke(filter: String) = flow {
        emit(apiRepository.getCocktailsByFirstLetter(filter).mapDrinksToResponseResult())
    }
}