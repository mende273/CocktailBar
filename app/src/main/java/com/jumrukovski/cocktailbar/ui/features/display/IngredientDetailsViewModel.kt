package com.jumrukovski.cocktailbar.ui.features.display

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.cocktailbar.domain.usecase.SearchIngredientUseCase
import com.jumrukovski.cocktailbar.domain.mapper.asIngredientUIState

class IngredientDetailsViewModel(private val searchIngredient: SearchIngredientUseCase) :
    ViewModel() {

    fun fetchData(name: String) = searchIngredient.invoke(name).asIngredientUIState(viewModelScope)
}