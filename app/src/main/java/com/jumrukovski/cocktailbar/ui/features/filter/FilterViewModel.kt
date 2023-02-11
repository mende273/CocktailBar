package com.jumrukovski.cocktailbar.ui.features.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.cocktailbar.domain.usecase.GetFilterListUseCase
import com.jumrukovski.cocktailbar.data.model.Filter
import com.jumrukovski.cocktailbar.domain.mapper.mapAsDrinksUIState

class FilterViewModel(private val getFilterList: GetFilterListUseCase) : ViewModel() {

    enum class FilterOption(val title: String, val param: String) {
        ALCOHOLIC("Alcoholic", "a"),
        INGREDIENTS("Ingredients", "i"),
        GLASS("Glass", "g"),
        DRINK("Drink", "c")
    }

    private var currentFilter = FilterOption.ALCOHOLIC

    fun getSupportedFilters() = FilterOption.values().map { it.title }

    fun setCurrentFilter(name: String) {
        FilterOption.values().forEach {
            if (it.title == name) {
                currentFilter = it
            }
        }
    }

    fun fetchData() = getFilterList.invoke(currentFilter.param, "list").mapAsDrinksUIState(viewModelScope)

    fun createFilter(value: String): Filter {
        return Filter(currentFilter.title, currentFilter.param, value)
    }
}