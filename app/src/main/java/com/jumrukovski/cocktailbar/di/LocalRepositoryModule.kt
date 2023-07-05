package com.jumrukovski.cocktailbar.di

import com.jumrukovski.cocktailbar.data.repository.LocalRepository
import com.jumrukovski.cocktailbar.domain.usecase.AddFavoriteDrinkInDBUseCase
import com.jumrukovski.cocktailbar.domain.usecase.GetAllFavoriteFromDBUseCase
import com.jumrukovski.cocktailbar.domain.usecase.GetFavoriteDrinkFromDBUseCase
import com.jumrukovski.cocktailbar.domain.usecase.RemoveFavoriteDrinkFromDBUseCase
import org.koin.dsl.module

val localRepositoryModule = module {
    single { LocalRepository(get()) }
    factory { AddFavoriteDrinkInDBUseCase(get()) }
    factory { RemoveFavoriteDrinkFromDBUseCase(get()) }
    factory { GetFavoriteDrinkFromDBUseCase(get()) }
    factory { GetAllFavoriteFromDBUseCase(get()) }
}
