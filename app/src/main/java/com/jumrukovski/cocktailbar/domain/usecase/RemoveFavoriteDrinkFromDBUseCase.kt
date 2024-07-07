package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.data.model.Drink

fun interface RemoveFavoriteDrinkFromDBUseCase {
    suspend operator fun invoke(drink: Drink)
}
