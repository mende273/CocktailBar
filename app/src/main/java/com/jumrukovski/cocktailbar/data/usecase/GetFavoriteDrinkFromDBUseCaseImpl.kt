package com.jumrukovski.cocktailbar.data.usecase

import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.domain.repository.local.LocalRepository
import com.jumrukovski.cocktailbar.domain.usecase.GetFavoriteDrinkFromDBUseCase

class GetFavoriteDrinkFromDBUseCaseImpl(private val localRepository: LocalRepository) :
    GetFavoriteDrinkFromDBUseCase {

    override suspend fun invoke(id: Long): Drink? = localRepository.getFavorite(id)
}
