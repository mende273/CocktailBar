package com.jumrukovski.cocktailbar.ui.features.home

import androidx.lifecycle.ViewModel
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.domain.usecase.GetCocktailsByFirstLetterUseCase
import com.jumrukovski.cocktailbar.ui.mapper.asFlowWithResult
import com.jumrukovski.cocktailbar.ui.mapper.mapResponseResultToDrinksUIState
import com.jumrukovski.cocktailbar.ui.state.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeViewModel(private val getCocktailsByFirstLetter: GetCocktailsByFirstLetterUseCase) :
    ViewModel() {

    val filters = listOf('A'..'Z').flatten()

    suspend fun fetchItems(filterPosition: Int): Flow<UIState<List<Drink>>> {
        val filter = filters[filterPosition].toString()

        return flow {
            emit(getCocktailsByFirstLetter.invoke(filter).mapResponseResultToDrinksUIState())
        }.asFlowWithResult()
    }
}