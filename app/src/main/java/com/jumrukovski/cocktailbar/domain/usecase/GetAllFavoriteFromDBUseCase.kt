package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.data.repository.LocalRepository

class GetAllFavoriteFromDBUseCase(private val localRepository: LocalRepository) {

    fun getAllFavoriteDrinks() = localRepository.getAllFavoriteDrinks()
}
