package com.jumrukovski.cocktailbar.data.usecase

import com.jumrukovski.cocktailbar.domain.repository.remote.RemoteRepository
import com.jumrukovski.cocktailbar.domain.usecase.SearchIngredientUseCase

class SearchIngredientUseCaseImpl(private val remoteRepository: RemoteRepository) :
    SearchIngredientUseCase {

    override suspend operator fun invoke(name: String) =
        remoteRepository.searchIngredient(name)
}
