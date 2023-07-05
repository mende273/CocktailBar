package com.jumrukovski.cocktailbar.ui.features.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.data.model.Filter
import com.jumrukovski.cocktailbar.domain.usecase.GetFilteredDrinksUseCase
import com.jumrukovski.cocktailbar.ui.mapper.mapResponseResultToDrinksUIStateFlow
import com.jumrukovski.cocktailbar.ui.state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch

class FilteredDrinksViewModel(private val getFilteredDrinks: GetFilteredDrinksUseCase) :
    ViewModel() {

    private val _uiState: MutableStateFlow<UIState<List<Drink>>> = MutableStateFlow(
        UIState.Loading(true)
    )
    val uiState: StateFlow<UIState<List<Drink>>> = _uiState.asStateFlow()

    fun requestData(filter: Filter) {
        viewModelScope.launch {
            val responseResult = getFilteredDrinks(filter.param, filter.prepareTypeParam())
            _uiState.emitAll(responseResult.mapResponseResultToDrinksUIStateFlow())
        }
    }
}
