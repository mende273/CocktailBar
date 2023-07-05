package com.jumrukovski.cocktailbar.di

import com.jumrukovski.cocktailbar.data.repository.ApiRepository
import com.jumrukovski.cocktailbar.domain.usecase.GetCocktailsByFirstLetterUseCase
import com.jumrukovski.cocktailbar.domain.usecase.GetDrinkDetailsUseCase
import com.jumrukovski.cocktailbar.domain.usecase.GetFilterListUseCase
import com.jumrukovski.cocktailbar.domain.usecase.GetFilteredDrinksUseCase
import com.jumrukovski.cocktailbar.domain.usecase.SearchDrinksByNameUseCase
import com.jumrukovski.cocktailbar.domain.usecase.SearchIngredientUseCase
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
