package com.jumrukovski.cocktailbar.injection

import com.jumrukovski.cocktailbar.features.display.DrinkDetailsViewModel
import com.jumrukovski.cocktailbar.features.display.IngredientDetailsViewModel
import com.jumrukovski.cocktailbar.features.favorite.FavoriteCocktailsViewModel
import com.jumrukovski.cocktailbar.features.filter.FilterViewModel
import com.jumrukovski.cocktailbar.features.filter.FilteredDrinksViewModel
import com.jumrukovski.cocktailbar.features.home.HomeViewModel
import com.jumrukovski.cocktailbar.features.main.MainViewModel
import com.jumrukovski.cocktailbar.features.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FilterViewModel(get()) }
    viewModel { FavoriteCocktailsViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { MainViewModel() }
    viewModel { FilteredDrinksViewModel(get()) }
    viewModel { DrinkDetailsViewModel(get(), get(), get(), get()) }
    viewModel { IngredientDetailsViewModel(get()) }
}