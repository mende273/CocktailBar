package com.jumrukovski.cocktailbar.ui.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.cocktailbar.domain.usecase.SearchDrinksByNameUseCase
import com.jumrukovski.cocktailbar.domain.mapper.asDrinksUIState

class SearchViewModel(private val searchDrinksByName: SearchDrinksByNameUseCase) :
    ViewModel() {

    fun fetchData(name: String) = searchDrinksByName.invoke(name).asDrinksUIState(viewModelScope)
}