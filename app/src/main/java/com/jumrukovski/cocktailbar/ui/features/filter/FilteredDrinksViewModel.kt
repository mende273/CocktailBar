package com.jumrukovski.cocktailbar.ui.features.filter

import androidx.lifecycle.ViewModel
import com.jumrukovski.cocktailbar.data.model.Filter
import com.jumrukovski.cocktailbar.domain.usecase.GetFilteredDrinksUseCase
import com.jumrukovski.cocktailbar.ui.mapper.asFlowWithResult
import com.jumrukovski.cocktailbar.ui.mapper.mapResponseResultToDrinksUIState
import kotlinx.coroutines.flow.flow

class FilteredDrinksViewModel(private val getFilteredDrinks: GetFilteredDrinksUseCase) :
    ViewModel() {

    suspend fun fetchItems(filter: Filter) = flow {
        emit(getFilteredDrinks.invoke(filter.param, filter.prepareTypeParam())
            .mapResponseResultToDrinksUIState())
    }.asFlowWithResult()
}