package com.jumrukovski.cocktailbar.ui.features.search

import androidx.lifecycle.ViewModel
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.domain.usecase.SearchDrinksByNameUseCase
import com.jumrukovski.cocktailbar.ui.mapper.asFlowWithResult
import com.jumrukovski.cocktailbar.ui.mapper.mapResponseResultToDrinksUIState
import com.jumrukovski.cocktailbar.ui.state.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchViewModel(private val searchDrinksByName: SearchDrinksByNameUseCase) :
    ViewModel() {

    suspend fun fetchData(name: String):Flow<UIState<List<Drink>>> {
        return flow {
            emit(searchDrinksByName.invoke(name).mapResponseResultToDrinksUIState())
        }.asFlowWithResult()
    }
}