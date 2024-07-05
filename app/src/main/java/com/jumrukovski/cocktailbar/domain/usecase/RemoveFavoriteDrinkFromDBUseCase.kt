package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.domain.repository.local.LocalRepository

class RemoveFavoriteDrinkFromDBUseCase(private val localRepository: LocalRepository) {

    fun removeFavorite(drink: Drink) {
        localRepository.removeFavorite(drink)
    }
}
