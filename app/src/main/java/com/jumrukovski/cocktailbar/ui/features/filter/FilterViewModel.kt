package com.jumrukovski.cocktailbar.ui.features.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.data.model.Filter
import com.jumrukovski.cocktailbar.domain.usecase.GetFilterListUseCase
import com.jumrukovski.cocktailbar.ui.mapper.mapResponseResultToDrinksUIStateFlow
import com.jumrukovski.cocktailbar.ui.state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch

class FilterViewModel(private val getFilterList: GetFilterListUseCase) : ViewModel() {

    private val _uiState: MutableStateFlow<UIState<List<Drink>>> = MutableStateFlow(
        UIState.Loading(true)
    )
    val uiState: StateFlow<UIState<List<Drink>>> = _uiState.asStateFlow()

    enum class FilterOption(val title: String, val param: String) {
        ALCOHOLIC("Alcoholic", "a"),
        INGREDIENTS("Ingredients", "i"),
        GLASS("Glass", "g"),
        DRINK("Drink", "c")
    }

    private var currentFilter = FilterOption.ALCOHOLIC

    fun getSupportedFilters() = FilterOption.values().map { it.title }

    private fun setCurrentFilter(name: String) {
        FilterOption.values().forEach {
            if (it.title == name) {
                currentFilter = it
            }
        }
    }

    fun requestData(filter: String) {
        setCurrentFilter(filter)
        viewModelScope.launch {
            val responseResult = getFilterList(currentFilter.param, "list")
            _uiState.emitAll(responseResult.mapResponseResultToDrinksUIStateFlow())
        }
    }

    fun createFilter(value: String): Filter {
        return Filter(currentFilter.title, currentFilter.param, value)
    }
}
