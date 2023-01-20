package com.jumrukovski.cocktailbar.di

import com.jumrukovski.cocktailbar.data.repository.ApiRepository
import com.jumrukovski.cocktailbar.domain.usecase.*
import org.koin.dsl.module

val apiRepositoryModule = module {
    single { ApiRepository(provideService(get())) }
    factory { GetCocktailsByFirstLetterUseCase(get()) }
    factory { SearchDrinksByNameUseCase(get()) }
    factory { GetFilteredDrinksUseCase(get()) }
    factory { GetFilterListUseCase(get()) }
    factory { GetDrinkDetailsUseCase(get()) }
    factory { SearchIngredientUseCase(get()) }
}