package com.jumrukovski.cocktailbar.ui.features.display

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.cocktailbar.data.model.Ingredient
import com.jumrukovski.cocktailbar.domain.usecase.SearchIngredientUseCase
import com.jumrukovski.cocktailbar.ui.mapper.asFlowWithResult
import com.jumrukovski.cocktailbar.ui.mapper.mapResponseResultToIngredientUIState
import com.jumrukovski.cocktailbar.ui.state.UIState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class IngredientDetailsViewModel(private val searchIngredient: SearchIngredientUseCase) :
    ViewModel() {

    private val _uiState: MutableStateFlow<UIState<Ingredient>> = MutableStateFlow(UIState.Loading(false))
    val uiState: StateFlow<UIState<Ingredient>> = _uiState.asStateFlow()

    fun requestData(name: String) {
        viewModelScope.launch {
            val responseResult = searchIngredient.invoke(name)
            _uiState.emitAll(flowOf(responseResult.mapResponseResultToIngredientUIState()).asFlowWithResult())
        }
    }
}