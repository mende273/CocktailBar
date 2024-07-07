package com.jumrukovski.cocktailbar.data.usecase

import com.jumrukovski.cocktailbar.domain.repository.remote.RemoteRepository
import com.jumrukovski.cocktailbar.domain.usecase.GetDrinkDetailsUseCase

class GetDrinkDetailsUseCaseImpl(private val remoteRepository: RemoteRepository) :
    GetDrinkDetailsUseCase {

    override suspend operator fun invoke(drinkId: Long) =
        remoteRepository.getDrinkDetails(drinkId)
}
