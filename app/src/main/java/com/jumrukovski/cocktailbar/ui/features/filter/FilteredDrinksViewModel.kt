package com.jumrukovski.cocktailbar.ui.features.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.cocktailbar.data.model.Filter
import com.jumrukovski.cocktailbar.ui.mapper.mapAsDrinksUIState
import com.jumrukovski.cocktailbar.domain.usecase.GetFilteredDrinksUseCase

class FilteredDrinksViewModel(private val getFilteredDrinks: GetFilteredDrinksUseCase) :
    ViewModel() {

    fun fetchItems(filter: Filter) =
        getFilteredDrinks.invoke(filter.param, filter.prepareTypeParam())
            .mapAsDrinksUIState(viewModelScope)
}