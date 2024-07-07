package com.jumrukovski.cocktailbar.ui.mapper

import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.data.model.Ingredient
import com.jumrukovski.cocktailbar.ui.state.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

suspend fun Result<Ingredient?>.mapResultToIngredientUIStateFlow(): Flow<UIState<Ingredient>> {
    val uiState = this.fold(onSuccess = {
        when (it == null) {
            true -> UIState.SuccessWithNoData
            false -> UIState.SuccessWithData(it)
        }
    }, onFailure = {
            UIState.Exception(it)
        })

    return flowOf(uiState).asFlowWithResult()
}

suspend fun Result<List<Drink>?>.mapResultToDrinksUIStateFlow(): Flow<UIState<List<Drink>>> {
    val uiState = this.fold(onSuccess = {
        when (it.isNullOrEmpty()) {
            true -> UIState.SuccessWithNoData
            false -> UIState.SuccessWithData(it)
        }
    }, onFailure = {
            UIState.Exception(it)
        })

    return flowOf(uiState).asFlowWithResult()
}

suspend fun Result<Drink?>.mapResultToDrinkUIStateFlow(): Flow<UIState<Drink>> {
    val uiState = this.fold(onSuccess = {
        when (it == null) {
            true -> UIState.SuccessWithNoData
            false -> UIState.SuccessWithData(it)
        }
    }, onFailure = {
            UIState.Exception(it)
        })

    return flowOf(uiState).asFlowWithResult()
}

private suspend fun <T> Flow<UIState<T>>.asFlowWithResult(): Flow<UIState<T>> {
    return this
        .onStart { emit(UIState.Loading(true)) }
        .onCompletion { emit(UIState.Loading(false)) }
        .catch { emit(UIState.Exception(it)) }
}
