package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.data.model.Drink

fun interface GetFilteredDrinksUseCase {
    suspend operator fun invoke(param: String, value: String): Result<List<Drink>>
}
