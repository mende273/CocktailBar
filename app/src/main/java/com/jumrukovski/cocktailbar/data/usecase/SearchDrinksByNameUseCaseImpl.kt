package com.jumrukovski.cocktailbar.data.usecase

import com.jumrukovski.cocktailbar.domain.repository.remote.RemoteRepository
import com.jumrukovski.cocktailbar.domain.usecase.SearchDrinksByNameUseCase

class SearchDrinksByNameUseCaseImpl(private val remoteRepository: RemoteRepository) :
    SearchDrinksByNameUseCase {

    override suspend operator fun invoke(name: String) =
        remoteRepository.searchDrinksByName(name)
}
