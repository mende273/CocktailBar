package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.base.BaseUseCase
import com.jumrukovski.cocktailbar.domain.mapper.mapDrinksToResponseData
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.data.repository.ApiRepository
import kotlinx.coroutines.flow.flow

class SearchDrinksByNameUseCase(private val apiRepository: ApiRepository) :
    BaseUseCase<List<Drink>>() {

    override fun invoke(vararg values: String) = flow {
        val response = apiRepository.searchDrinksByName(values[0])

        emit(response.mapDrinksToResponseData())
    }
}