package com.jumrukovski.cocktailbar.data.usecase

import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.domain.repository.local.LocalRepository
import com.jumrukovski.cocktailbar.domain.usecase.RemoveFavoriteDrinkFromDBUseCase

class RemoveFavoriteDrinkFromDBUseCaseImpl(private val localRepository: LocalRepository) :
    RemoveFavoriteDrinkFromDBUseCase {

    override suspend fun invoke(drink: Drink) {
        localRepository.removeFavorite(drink)
    }
}
