package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.domain.repository.local.LocalRepository

class GetFavoriteDrinkFromDBUseCase(private val localRepository: LocalRepository) {

    fun getFavoriteDrink(id: Long) = localRepository.getFavorite(id)
}
