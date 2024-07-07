package com.jumrukovski.cocktailbar.di

import com.jumrukovski.cocktailbar.data.repository.local.LocalRepositoryImpl
import com.jumrukovski.cocktailbar.data.usecase.AddFavoriteDrinkInDBUseCaseImpl
import com.jumrukovski.cocktailbar.data.usecase.GetAllFavoriteFromDBUseCaseImpl
import com.jumrukovski.cocktailbar.data.usecase.GetFavoriteDrinkFromDBUseCaseImpl
import com.jumrukovski.cocktailbar.data.usecase.RemoveFavoriteDrinkFromDBUseCaseImpl
import com.jumrukovski.cocktailbar.domain.repository.local.LocalRepository
import com.jumrukovski.cocktailbar.domain.usecase.AddFavoriteDrinkInDBUseCase
import com.jumrukovski.cocktailbar.domain.usecase.GetAllFavoriteFromDBUseCase
import com.jumrukovski.cocktailbar.domain.usecase.GetFavoriteDrinkFromDBUseCase
import com.jumrukovski.cocktailbar.domain.usecase.RemoveFavoriteDrinkFromDBUseCase
import org.koin.dsl.module

val localRepositoryModule = module {
    single<LocalRepository> { LocalRepositoryImpl(get(), get()) }
    factory<AddFavoriteDrinkInDBUseCase> { AddFavoriteDrinkInDBUseCaseImpl(get()) }
    factory<RemoveFavoriteDrinkFromDBUseCase> { RemoveFavoriteDrinkFromDBUseCaseImpl(get()) }
    factory<GetFavoriteDrinkFromDBUseCase> { GetFavoriteDrinkFromDBUseCaseImpl(get()) }
    factory<GetAllFavoriteFromDBUseCase> { GetAllFavoriteFromDBUseCaseImpl(get()) }
}
