package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.data.repository.ApiRepository
import com.jumrukovski.cocktailbar.domain.mapper.mapDrinkToResponseResult
import kotlinx.coroutines.flow.flow

class GetDrinkDetailsUseCase(private val apiRepository: ApiRepository) {

    fun invoke(drinkId: Long) = flow {
        emit(apiRepository.getDrinkDetails(drinkId).mapDrinkToResponseResult())
    }
}