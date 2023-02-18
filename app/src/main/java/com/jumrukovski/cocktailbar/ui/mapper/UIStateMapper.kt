package com.jumrukovski.cocktailbar.ui.mapper

import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.data.model.Ingredient
import com.jumrukovski.cocktailbar.data.network.ResponseResult
import com.jumrukovski.cocktailbar.ui.state.UIState
import kotlinx.coroutines.flow.*

fun ResponseResult<Ingredient>.mapResponseResultToIngredientUIState(): UIState<Ingredient> {
    return when (this) {
        is ResponseResult.Error -> UIState.Error(this.code)
        is ResponseResult.Exception -> UIState.Exception(this.exception)
        is ResponseResult.Success -> this.data?.let {
            UIState.SuccessWithData(it)
        } ?: UIState.SuccessWithNoData
    }
}

suspend fun ResponseResult<List<Drink>>.mapResponseResultToDrinksUIStateFlow(): Flow<UIState<List<Drink>>> {
    val uiState = when (this) {
        is ResponseResult.Success ->
            when (this.data.isNullOrEmpty()) {
                true -> UIState.SuccessWithNoData
                false -> UIState.SuccessWithData(this.data)
            }
        is ResponseResult.Error -> UIState.Error(this.code)
        is ResponseResult.Exception -> UIState.Exception(this.exception)
    }

    return flowOf(uiState).asFlowWithResult()
}

suspend fun ResponseResult<Drink>.mapResponseResultToDrinkUIStateFlow(): Flow<UIState<Drink>> {
    val uiState =  when (this) {
        is ResponseResult.Error -> UIState.Error(this.code)
        is ResponseResult.Exception -> UIState.Exception(this.exception)
        is ResponseResult.Success -> this.data?.let {
            UIState.SuccessWithData(it)
        } ?: UIState.SuccessWithNoData
    }

    return flowOf(uiState).asFlowWithResult()
}

suspend fun <T> Flow<UIState<T>>.asFlowWithResult(): Flow<UIState<T>> {
    return this
        .onStart { emit(UIState.Loading(true)) }
        .onCompletion { emit(UIState.Loading(false)) }
        .catch { emit(UIState.Exception(it)) }
}