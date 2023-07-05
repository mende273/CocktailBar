package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.data.repository.LocalRepository

class AddFavoriteDrinkInDBUseCase(private val localRepository: LocalRepository) {

    fun addFavorite(drink: Drink) {
        localRepository.addFavorite(drink)
    }
}
