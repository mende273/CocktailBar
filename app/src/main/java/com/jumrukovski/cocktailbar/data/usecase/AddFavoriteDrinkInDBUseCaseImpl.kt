package com.jumrukovski.cocktailbar.data.usecase

import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.domain.repository.local.LocalRepository
import com.jumrukovski.cocktailbar.domain.usecase.AddFavoriteDrinkInDBUseCase

class AddFavoriteDrinkInDBUseCaseImpl(private val localRepository: LocalRepository) :
    AddFavoriteDrinkInDBUseCase {

    override fun invoke(drink: Drink) {
        localRepository.addFavorite(drink)
    }
}
