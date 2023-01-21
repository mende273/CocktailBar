package com.jumrukovski.cocktailbar.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.cocktailbar.domain.usecase.GetCocktailsByFirstLetterUseCase
import com.jumrukovski.cocktailbar.domain.mapper.asDrinksUIState
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.ui.state.UIState
import kotlinx.coroutines.flow.Flow

class HomeViewModel(private val getCocktailsByFirstLetter: GetCocktailsByFirstLetterUseCase) :
    ViewModel() {

    val filters = listOf('A'..'Z').flatten()

    fun fetchItems(filterPosition: Int): Flow<UIState<List<Drink>>> {
        val filter = filters[filterPosition].toString()

        return getCocktailsByFirstLetter.invoke(filter).asDrinksUIState(viewModelScope)
    }
}