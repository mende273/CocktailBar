package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.data.mapper.mapDrinkToResponseResult
import com.jumrukovski.cocktailbar.domain.repository.remote.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetDrinkDetailsUseCase(private val remoteRepository: RemoteRepository) {

    suspend operator fun invoke(drinkId: Long) = withContext(Dispatchers.IO) {
        return@withContext remoteRepository.getDrinkDetailsAsync(drinkId).mapDrinkToResponseResult()
    }
}
