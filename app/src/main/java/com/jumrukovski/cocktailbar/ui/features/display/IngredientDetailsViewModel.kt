package com.jumrukovski.cocktailbar.ui.features.display

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.cocktailbar.data.model.Ingredient
import com.jumrukovski.cocktailbar.domain.usecase.SearchIngredientUseCase
import com.jumrukovski.cocktailbar.ui.mapper.mapResponseResultToIngredientUIStateFlow
import com.jumrukovski.cocktailbar.ui.state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch

class IngredientDetailsViewModel(private val searchIngredient: SearchIngredientUseCase) :
    ViewModel() {

    private val _uiState: MutableStateFlow<UIState<Ingredient>> =
        MutableStateFlow(UIState.Loading(false))
    val uiState: StateFlow<UIState<Ingredient>> = _uiState.asStateFlow()

    fun requestData(name: String) {
        viewModelScope.launch {
            val responseResult = searchIngredient(name)
            _uiState.emitAll(responseResult.mapResponseResultToIngredientUIStateFlow())
        }
    }
}
