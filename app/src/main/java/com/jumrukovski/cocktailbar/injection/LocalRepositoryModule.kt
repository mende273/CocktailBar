package com.jumrukovski.cocktailbar.injection

import com.jumrukovski.cocktailbar.data.domain.AddFavoriteDrinkInDBUseCase
import com.jumrukovski.cocktailbar.data.domain.GetAllFavoriteFromDBUseCase
import com.jumrukovski.cocktailbar.data.domain.GetFavoriteDrinkFromDBUseCase
import com.jumrukovski.cocktailbar.data.domain.RemoveFavoriteDrinkFromDBUseCase
import com.jumrukovski.cocktailbar.data.repository.LocalRepository
import org.koin.dsl.module

val localRepositoryModule = module {
    single { LocalRepository(get()) }
    factory { AddFavoriteDrinkInDBUseCase(get()) }
    factory { RemoveFavoriteDrinkFromDBUseCase(get()) }
    factory { GetFavoriteDrinkFromDBUseCase(get()) }
    factory { GetAllFavoriteFromDBUseCase(get()) }
}