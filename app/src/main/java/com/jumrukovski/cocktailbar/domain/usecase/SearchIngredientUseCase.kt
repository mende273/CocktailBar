package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.data.model.Ingredient

fun interface SearchIngredientUseCase {
    suspend operator fun invoke(name: String): Result<Ingredient>
}
