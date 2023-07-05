package com.jumrukovski.cocktailbar.ui.mapper

import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.data.model.Ingredient
import com.jumrukovski.cocktailbar.data.network.ResponseResult
import com.jumrukovski.cocktailbar.ui.state.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

suspend fun ResponseResult<Ingredient>.mapResponseResultToIngredientUIStateFlow(): Flow<UIState<Ingredient>> {
    val uiState = when (this) {
        is ResponseResult.Error -> UIState.Error(this.code)
        is ResponseResult.Exception -> UIState.Exception(this.exception)
        is ResponseResult.Success -> this.data?.let {
            UIState.SuccessWithData(it)
        } ?: UIState.SuccessWithNoData
    }

    return flowOf(uiState).asFlowWithResult()
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
    val uiState = when (this) {
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
