package com.jumrukovski.cocktailbar.ui.features.filter

import androidx.lifecycle.ViewModel
import com.jumrukovski.cocktailbar.data.model.Filter
import com.jumrukovski.cocktailbar.domain.usecase.GetFilterListUseCase
import com.jumrukovski.cocktailbar.ui.mapper.asFlowWithResult
import com.jumrukovski.cocktailbar.ui.mapper.mapResponseResultToDrinksUIState
import kotlinx.coroutines.flow.flow

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

    suspend fun fetchData() = flow {
        emit(getFilterList.invoke(currentFilter.param, "list").mapResponseResultToDrinksUIState())
    }.asFlowWithResult()

    fun createFilter(value: String): Filter {
        return Filter(currentFilter.title, currentFilter.param, value)
    }
}