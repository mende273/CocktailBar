package com.jumrukovski.cocktailbar.data.domain

import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.data.repository.LocalRepository

class RemoveFavoriteDrinkFromDBUseCase(private val localRepository: LocalRepository) {

    fun removeFavorite(drink: Drink) {
        localRepository.removeFavorite(drink)
    }
}