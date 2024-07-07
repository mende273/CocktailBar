package com.jumrukovski.cocktailbar.data.usecase

import androidx.lifecycle.LiveData
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.domain.repository.local.LocalRepository
import com.jumrukovski.cocktailbar.domain.usecase.GetAllFavoriteFromDBUseCase

class GetAllFavoriteFromDBUseCaseImpl(private val localRepository: LocalRepository) :
    GetAllFavoriteFromDBUseCase {

    override fun invoke(): LiveData<List<Drink>> = localRepository.getAllFavoriteDrinks()
}
