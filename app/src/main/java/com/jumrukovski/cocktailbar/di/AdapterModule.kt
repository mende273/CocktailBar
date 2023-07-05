package com.jumrukovski.cocktailbar.di

import com.jumrukovski.cocktailbar.ui.features.display.DisplayDrinksAdapter
import com.jumrukovski.cocktailbar.ui.features.display.IngredientsAdapter
import com.jumrukovski.cocktailbar.ui.features.filter.FilterAdapter
import org.koin.dsl.module

val adapterModule = module {
    factory { DisplayDrinksAdapter() }
    single { FilterAdapter() }
    single { IngredientsAdapter() }
}
