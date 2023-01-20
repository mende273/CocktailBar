package com.jumrukovski.cocktailbar.features.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.cocktailbar.data.domain.GetFilterListUseCase
import com.jumrukovski.cocktailbar.data.domain.mappers.asDrinksUIState
import com.jumrukovski.cocktailbar.data.model.Filter

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

    fun fetchData() = getFilterList.invoke(currentFilter.param, "list").asDrinksUIState(viewModelScope)

    fun createFilter(value: String): Filter {
        return Filter(currentFilter.title, currentFilter.param, value)
    }
}