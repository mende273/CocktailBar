package com.jumrukovski.cocktailbar.features.display

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.cocktailbar.data.domain.SearchIngredientUseCase
import com.jumrukovski.cocktailbar.data.domain.mappers.asIngredientUIState

class IngredientDetailsViewModel(private val searchIngredient: SearchIngredientUseCase) :
    ViewModel() {

    fun fetchData(name: String) = searchIngredient.invoke(name).asIngredientUIState(viewModelScope)
}