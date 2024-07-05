package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.data.model.Drink

interface AddFavoriteDrinkInDBUseCase {
    operator fun invoke(drink: Drink)
}
