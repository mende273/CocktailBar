package com.jumrukovski.cocktailbar.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.domain.usecase.GetCocktailsByFirstLetterUseCase
import com.jumrukovski.cocktailbar.ui.mapper.mapResponseResultToDrinksUIStateFlow
import com.jumrukovski.cocktailbar.ui.state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch

class HomeViewModel(private val getCocktailsByFirstLetter: GetCocktailsByFirstLetterUseCase) :
    ViewModel() {

    private val _uiState: MutableStateFlow<UIState<List<Drink>>> = MutableStateFlow(
        UIState.Loading(true)
    )
    val uiState: StateFlow<UIState<List<Drink>>> = _uiState.asStateFlow()

    val filters = listOf('A'..'Z').flatten()

    fun requestData(filterPosition: Int) {
        val filter = filters[filterPosition].toString()

        viewModelScope.launch {
            val responseResult = getCocktailsByFirstLetter(filter)
            _uiState.emitAll(responseResult.mapResponseResultToDrinksUIStateFlow())
        }
    }
}
