package com.jumrukovski.cocktailbar.domain.mapper

import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.data.model.Ingredient
import com.jumrukovski.cocktailbar.data.network.ResponseResult
import com.jumrukovski.cocktailbar.ui.state.UIState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

fun Flow<ResponseResult<Ingredient>>.mapAsIngredientUIState(scope: CoroutineScope): StateFlow<UIState<Ingredient>> {
    return this.map { response ->
        when (response) {
            is ResponseResult.Success ->
                response.data?.let {
                    UIState.Success(it)
                } ?: UIState.NoData
            is ResponseResult.Error -> UIState.Error(response.code)
            is ResponseResult.Exception -> UIState.Exception(response.exception)
        }
    }.asResult(scope)
}

fun Flow<ResponseResult<List<Drink>>>.mapAsDrinksUIState(scope: CoroutineScope): StateFlow<UIState<List<Drink>>> {
    return this.map { response ->
        when (response) {
            is ResponseResult.Success ->
                if (response.data.isNullOrEmpty()) {
                    UIState.NoData
                } else {
                    UIState.Success(response.data)
                }
            is ResponseResult.Error -> UIState.Error(response.code)
            is ResponseResult.Exception -> UIState.Error(-1)
        }
    }.asResult(scope)
}

fun Flow<ResponseResult<Drink>>.mapAsDrinkUIState(scope: CoroutineScope): StateFlow<UIState<Drink>> {
    return this.map { response ->
        when (response) {
            is ResponseResult.Success -> {
                response.data?.let {
                    UIState.Success(it)
                } ?: UIState.NoData
            }
            is ResponseResult.Error -> UIState.Error(response.code)
            is ResponseResult.Exception -> UIState.Error(-1)
        }
    }.asResult(scope)
}

private fun <T> Flow<UIState<T>>.asResult(scope: CoroutineScope): StateFlow<UIState<T>> {
    return this
        .onStart { emit(UIState.Loading(true)) }
        .onCompletion { UIState.Loading(false) }
        .catch { emit(UIState.Exception(it)) }
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UIState.Loading(false)
        )
}