package com.jumrukovski.cocktailbar.di

import com.jumrukovski.cocktailbar.ui.adapters.CocktailAdapter
import com.jumrukovski.cocktailbar.ui.adapters.FilterAdapter
import com.jumrukovski.cocktailbar.ui.adapters.IngredientsAdapter
import org.koin.dsl.module

val adapterModule = module {
    factory { CocktailAdapter() }
    single { FilterAdapter() }
    single { IngredientsAdapter() }
}