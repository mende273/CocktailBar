package com.jumrukovski.cocktailbar.data.usecase

import com.jumrukovski.cocktailbar.domain.repository.remote.RemoteRepository
import com.jumrukovski.cocktailbar.domain.usecase.GetFilteredDrinksUseCase

class GetFilteredDrinksUseCaseImpl(private val remoteRepository: RemoteRepository) :
    GetFilteredDrinksUseCase {

    override suspend operator fun invoke(param: String, value: String) =
        remoteRepository.getFilteredDrinks(param, value)
}
