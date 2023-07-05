package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.data.network.ResponseResult
import com.jumrukovski.cocktailbar.data.repository.ApiRepository
import com.jumrukovski.cocktailbar.domain.mapper.mapDrinksToResponseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCocktailsByFirstLetterUseCase(private val apiRepository: ApiRepository) {

    suspend operator fun invoke(filter: String): ResponseResult<List<Drink>> = withContext(
        Dispatchers.IO
    ) {
        return@withContext apiRepository.getCocktailsByFirstLetterAsync(filter).mapDrinksToResponseResult()
    }
}
