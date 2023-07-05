package com.jumrukovski.cocktailbar.di

import com.jumrukovski.cocktailbar.ui.features.display.DrinkDetailsViewModel
import com.jumrukovski.cocktailbar.ui.features.display.IngredientDetailsViewModel
import com.jumrukovski.cocktailbar.ui.features.favorite.FavoriteCocktailsViewModel
import com.jumrukovski.cocktailbar.ui.features.filter.FilterViewModel
import com.jumrukovski.cocktailbar.ui.features.filter.FilteredDrinksViewModel
import com.jumrukovski.cocktailbar.ui.features.home.HomeViewModel
import com.jumrukovski.cocktailbar.ui.features.main.MainViewModel
import com.jumrukovski.cocktailbar.ui.features.search.SearchViewModel
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
