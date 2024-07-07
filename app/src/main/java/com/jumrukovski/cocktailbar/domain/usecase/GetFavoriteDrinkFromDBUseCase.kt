package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.data.model.Drink

fun interface GetFavoriteDrinkFromDBUseCase {
    suspend operator fun invoke(id: Long): Drink?
}
