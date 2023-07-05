package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.data.repository.ApiRepository
import com.jumrukovski.cocktailbar.domain.mapper.mapDrinksToResponseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchDrinksByNameUseCase(private val apiRepository: ApiRepository) {

    suspend operator fun invoke(name: String) = withContext(Dispatchers.IO) {
        return@withContext apiRepository.searchDrinksByNameAsync(name).mapDrinksToResponseResult()
    }
}
