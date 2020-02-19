package com.jumrukovski.cocktailbar.data.domain

import com.jumrukovski.cocktailbar.data.repository.LocalRepository

class GetFavoriteDrinkFromDBUseCase(private val localRepository: LocalRepository) {

    fun getFavoriteDrink(id: Long) = localRepository.getFavorite(id)
}