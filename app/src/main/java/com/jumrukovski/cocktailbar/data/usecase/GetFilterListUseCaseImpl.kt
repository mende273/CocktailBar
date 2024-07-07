package com.jumrukovski.cocktailbar.data.usecase

import com.jumrukovski.cocktailbar.domain.repository.remote.RemoteRepository
import com.jumrukovski.cocktailbar.domain.usecase.GetFilterListUseCase

class GetFilterListUseCaseImpl(private val remoteRepository: RemoteRepository) :
    GetFilterListUseCase {

    override suspend operator fun invoke(currentFilterParam: String, value: String) =
        remoteRepository.getFilterListForOption(currentFilterParam, value)
}
