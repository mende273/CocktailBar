package com.jumrukovski.cocktailbar.injection

import com.jumrukovski.cocktailbar.data.domain.*
import com.jumrukovski.cocktailbar.data.repository.ApiRepository
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