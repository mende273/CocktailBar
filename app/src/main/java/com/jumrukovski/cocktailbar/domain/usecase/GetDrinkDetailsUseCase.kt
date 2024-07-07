package com.jumrukovski.cocktailbar.domain.usecase

import com.jumrukovski.cocktailbar.data.model.Drink

fun interface GetDrinkDetailsUseCase {
    suspend operator fun invoke(drinkId: Long): Result<Drink>
}
