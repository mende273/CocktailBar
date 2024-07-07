package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.data.model.Drink

fun interface AddFavoriteDrinkInDBUseCase {
    suspend operator fun invoke(drink: Drink)
}
