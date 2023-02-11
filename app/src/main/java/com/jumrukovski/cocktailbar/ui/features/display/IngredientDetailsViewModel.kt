package com.jumrukovski.cocktailbar.ui.features.display

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.cocktailbar.domain.mapper.mapAsIngredientUIState
import com.jumrukovski.cocktailbar.domain.usecase.SearchIngredientUseCase

class IngredientDetailsViewModel(private val searchIngredient: SearchIngredientUseCase) :
    ViewModel() {

    fun fetchData(name: String) = searchIngredient.invoke(name).mapAsIngredientUIState(viewModelScope)
}