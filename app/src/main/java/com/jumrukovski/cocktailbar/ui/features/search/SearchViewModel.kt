package com.jumrukovski.cocktailbar.ui.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.domain.usecase.SearchDrinksByNameUseCase
import com.jumrukovski.cocktailbar.ui.mapper.mapResultToDrinksUIStateFlow
import com.jumrukovski.cocktailbar.ui.state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch

class SearchViewModel(private val searchDrinksByNameUseCase: SearchDrinksByNameUseCase) :
    ViewModel() {

    private val _uiState: MutableStateFlow<UIState<List<Drink>>> = MutableStateFlow(
        UIState.Loading(false)
    )
    val uiState: StateFlow<UIState<List<Drink>>> get() = _uiState

    fun requestData(name: String) {
        viewModelScope.launch {
            val responseResult = searchDrinksByNameUseCase(name)
            _uiState.emitAll(responseResult.mapResultToDrinksUIStateFlow())
        }
    }
}
