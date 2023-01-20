package com.jumrukovski.cocktailbar.data.domain

import com.jumrukovski.cocktailbar.base.BaseUseCase
import com.jumrukovski.cocktailbar.data.domain.mappers.mapDrinkToResponseData
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.data.repository.ApiRepository
import kotlinx.coroutines.flow.flow

class GetDrinkDetailsUseCase(private val apiRepository: ApiRepository) : BaseUseCase<Drink>() {

    override fun invoke(vararg values: String) = flow {
        val response = apiRepository.getDrinkDetails(values[0].toLong())

        emit(response.mapDrinkToResponseData())
    }
}