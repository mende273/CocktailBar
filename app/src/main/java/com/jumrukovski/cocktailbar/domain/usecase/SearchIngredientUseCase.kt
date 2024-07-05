package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.data.mapper.mapIngredientToResponseResult
import com.jumrukovski.cocktailbar.domain.repository.remote.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchIngredientUseCase(private val remoteRepository: RemoteRepository) {

    suspend operator fun invoke(name: String) = withContext(Dispatchers.IO) {
        return@withContext remoteRepository.searchIngredientAsync(name)
            .mapIngredientToResponseResult()
    }
}
