package com.jumrukovski.cocktailbar.domain.usecase

import androidx.lifecycle.LiveData
import com.jumrukovski.cocktailbar.data.model.Drink

interface GetAllFavoriteFromDBUseCase {
    operator fun invoke(): LiveData<List<Drink>>
}
