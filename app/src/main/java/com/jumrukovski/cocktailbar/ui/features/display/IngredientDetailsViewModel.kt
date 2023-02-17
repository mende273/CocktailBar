package com.jumrukovski.cocktailbar.ui.features.display

import androidx.lifecycle.ViewModel
import com.jumrukovski.cocktailbar.domain.usecase.SearchIngredientUseCase
import com.jumrukovski.cocktailbar.ui.mapper.asFlowWithResult
import com.jumrukovski.cocktailbar.ui.mapper.mapResponseResultToIngredientUIState
import kotlinx.coroutines.flow.flow

class IngredientDetailsViewModel(private val searchIngredient: SearchIngredientUseCase) :
    ViewModel() {

    suspend fun fetchData(name: String) = flow {
        emit(searchIngredient.invoke(name).mapResponseResultToIngredientUIState())
    }.asFlowWithResult()
}