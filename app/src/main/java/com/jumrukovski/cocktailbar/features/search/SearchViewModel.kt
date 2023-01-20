package com.jumrukovski.cocktailbar.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.cocktailbar.data.domain.SearchDrinksByNameUseCase
import com.jumrukovski.cocktailbar.data.domain.mappers.asDrinksUIState

class SearchViewModel(private val searchDrinksByName: SearchDrinksByNameUseCase) :
    ViewModel() {

    fun fetchData(name: String) = searchDrinksByName.invoke(name).asDrinksUIState(viewModelScope)
}