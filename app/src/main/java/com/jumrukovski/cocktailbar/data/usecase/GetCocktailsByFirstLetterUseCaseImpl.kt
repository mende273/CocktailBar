package com.jumrukovski.cocktailbar.data.usecase

import com.jumrukovski.cocktailbar.domain.repository.remote.RemoteRepository
import com.jumrukovski.cocktailbar.domain.usecase.GetCocktailsByFirstLetterUseCase

class GetCocktailsByFirstLetterUseCaseImpl(private val remoteRepository: RemoteRepository) :
    GetCocktailsByFirstLetterUseCase {

    override suspend operator fun invoke(filter: String) =
        remoteRepository.getCocktailsByFirstLetter(filter)
}
