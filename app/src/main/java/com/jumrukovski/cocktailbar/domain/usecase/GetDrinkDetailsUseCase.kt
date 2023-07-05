package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.data.repository.ApiRepository
import com.jumrukovski.cocktailbar.domain.mapper.mapDrinkToResponseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetDrinkDetailsUseCase(private val apiRepository: ApiRepository) {

    suspend operator fun invoke(drinkId: Long) = withContext(Dispatchers.IO) {
        return@withContext apiRepository.getDrinkDetailsAsync(drinkId).mapDrinkToResponseResult()
    }
}
