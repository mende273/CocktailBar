package com.jumrukovski.cocktailbar.features.filter

import androidx.lifecycle.ViewModel
import com.jumrukovski.cocktailbar.data.domain.GetFilteredDrinksUseCase
import com.jumrukovski.cocktailbar.data.model.Filter

class FilteredDrinksViewModel(private val getFilteredDrinks: GetFilteredDrinksUseCase) :
    ViewModel() {

    fun fetchItems(filter: Filter) = getFilteredDrinks.loadData(filter.param, filter.prepareTypeParam())
}