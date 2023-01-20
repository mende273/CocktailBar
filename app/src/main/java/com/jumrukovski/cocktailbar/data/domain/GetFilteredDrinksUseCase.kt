package com.jumrukovski.cocktailbar.data.domain

import com.jumrukovski.cocktailbar.base.BaseUseCase
import com.jumrukovski.cocktailbar.data.domain.mappers.mapDrinksToResponseData
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.data.repository.ApiRepository
import kotlinx.coroutines.flow.flow

class GetFilteredDrinksUseCase(private val apiRepository: ApiRepository) :
    BaseUseCase<List<Drink>>() {

    override fun invoke(vararg values: String) = flow {
        val response = apiRepository.getFilteredDrinks(values[0], values[1])

        emit(response.mapDrinksToResponseData())
    }
}