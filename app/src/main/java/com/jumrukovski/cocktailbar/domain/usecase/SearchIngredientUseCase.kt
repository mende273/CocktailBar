package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.data.repository.ApiRepository
import com.jumrukovski.cocktailbar.domain.mapper.mapIngredientToResponseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchIngredientUseCase(private val apiRepository: ApiRepository) {

    suspend operator fun invoke(name: String) = withContext(Dispatchers.IO) {
        return@withContext apiRepository.searchIngredientAsync(name).mapIngredientToResponseResult()
    }
}
