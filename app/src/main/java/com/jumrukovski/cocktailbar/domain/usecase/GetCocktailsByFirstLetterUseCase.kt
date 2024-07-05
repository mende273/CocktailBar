package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.data.mapper.mapDrinksToResponseResult
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.data.network.ResponseResult
import com.jumrukovski.cocktailbar.domain.repository.remote.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCocktailsByFirstLetterUseCase(private val remoteRepository: RemoteRepository) {

    suspend operator fun invoke(filter: String): ResponseResult<List<Drink>> = withContext(
        Dispatchers.IO
    ) {
        return@withContext remoteRepository
            .getCocktailsByFirstLetterAsync(filter)
            .mapDrinksToResponseResult()
    }
}
