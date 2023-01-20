package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.base.BaseUseCase
import com.jumrukovski.cocktailbar.domain.mapper.mapDrinksToResponseData
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.data.repository.ApiRepository
import kotlinx.coroutines.flow.flow

class GetFilterListUseCase(private val apiRepository: ApiRepository) :
    BaseUseCase<List<Drink>>() {

    override fun invoke(vararg values: String) = flow {
        val response = apiRepository.getFilterListFor(values[0], values[1])

        emit(response.mapDrinksToResponseData())
    }
}