package com.jumrukovski.cocktailbar.features.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.cocktailbar.domain.usecase.GetFilteredDrinksUseCase
import com.jumrukovski.cocktailbar.domain.mapper.asDrinksUIState
import com.jumrukovski.cocktailbar.data.model.Filter

class FilteredDrinksViewModel(private val getFilteredDrinks: GetFilteredDrinksUseCase) :
    ViewModel() {

    fun fetchItems(filter: Filter) = getFilteredDrinks.invoke(filter.param, filter.prepareTypeParam()).asDrinksUIState(viewModelScope)
}