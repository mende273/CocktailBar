package com.jumrukovski.cocktailbar.features.display

import androidx.lifecycle.ViewModel
import com.jumrukovski.cocktailbar.data.domain.SearchIngredientUseCase

class IngredientDetailsViewModel(private val searchIngredient: SearchIngredientUseCase) :
    ViewModel() {

    fun fetchData(name: String) = searchIngredient.loadData(name)
}