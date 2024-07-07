package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.data.model.Drink

fun interface GetCocktailsByFirstLetterUseCase {
    suspend operator fun invoke(filter: String): Result<List<Drink>>
}
