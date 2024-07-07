package com.jumrukovski.cocktailbar.di

import com.jumrukovski.cocktailbar.data.repository.remote.RemoteRepositoryImpl
import com.jumrukovski.cocktailbar.data.usecase.GetCocktailsByFirstLetterUseCaseImpl
import com.jumrukovski.cocktailbar.data.usecase.GetDrinkDetailsUseCaseImpl
import com.jumrukovski.cocktailbar.data.usecase.GetFilterListUseCaseImpl
import com.jumrukovski.cocktailbar.data.usecase.GetFilteredDrinksUseCaseImpl
import com.jumrukovski.cocktailbar.data.usecase.SearchDrinksByNameUseCaseImpl
import com.jumrukovski.cocktailbar.data.usecase.SearchIngredientUseCaseImpl
import com.jumrukovski.cocktailbar.domain.repository.remote.RemoteRepository
import com.jumrukovski.cocktailbar.domain.usecase.GetCocktailsByFirstLetterUseCase
import com.jumrukovski.cocktailbar.domain.usecase.GetDrinkDetailsUseCase
import com.jumrukovski.cocktailbar.domain.usecase.GetFilterListUseCase
import com.jumrukovski.cocktailbar.domain.usecase.GetFilteredDrinksUseCase
import com.jumrukovski.cocktailbar.domain.usecase.SearchDrinksByNameUseCase
import com.jumrukovski.cocktailbar.domain.usecase.SearchIngredientUseCase
import org.koin.dsl.module

val remoteRepositoryModule = module {
    single<RemoteRepository> { RemoteRepositoryImpl(provideRemoteDataSource(get()), get()) }
    factory<GetCocktailsByFirstLetterUseCase> { GetCocktailsByFirstLetterUseCaseImpl(get()) }
    factory<SearchDrinksByNameUseCase> { SearchDrinksByNameUseCaseImpl(get()) }
    factory<GetFilteredDrinksUseCase> { GetFilteredDrinksUseCaseImpl(get()) }
    factory<GetFilterListUseCase> { GetFilterListUseCaseImpl(get()) }
    factory<GetDrinkDetailsUseCase> { GetDrinkDetailsUseCaseImpl(get()) }
    factory<SearchIngredientUseCase> { SearchIngredientUseCaseImpl(get()) }
}
