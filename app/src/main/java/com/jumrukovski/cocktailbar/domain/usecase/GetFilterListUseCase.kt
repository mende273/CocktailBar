package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.data.model.Drink

fun interface GetFilterListUseCase {
    suspend operator fun invoke(
        currentFilterParam: String,
        value: String
    ): Result<List<Drink>>
}
