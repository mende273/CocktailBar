package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.data.mapper.mapDrinksToResponseResult
import com.jumrukovski.cocktailbar.domain.repository.remote.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetFilteredDrinksUseCase(private val remoteRepository: RemoteRepository) {

    suspend operator fun invoke(param: String, value: String) = withContext(Dispatchers.IO) {
        return@withContext remoteRepository
            .getFilteredDrinksAsync(param, value)
            .mapDrinksToResponseResult()
    }
}
