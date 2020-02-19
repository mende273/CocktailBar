package com.jumrukovski.cocktailbar.features.search

import androidx.lifecycle.ViewModel
import com.jumrukovski.cocktailbar.data.domain.SearchDrinksByNameUseCase

class SearchViewModel(private val searchDrinksByName: SearchDrinksByNameUseCase) :
    ViewModel() {

    fun fetchData(name: String) = searchDrinksByName.loadData(name)
}