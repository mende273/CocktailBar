package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.data.model.Drink

fun interface SearchDrinksByNameUseCase {
    suspend operator fun invoke(name: String): Result<List<Drink>>
}
