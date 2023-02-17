package com.jumrukovski.cocktailbar.ui.mapper

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
                    UIState.SuccessWithData(it)
                } ?: UIState.SuccessWithNoData
            is ResponseResult.Error -> UIState.Error(response.code)
            is ResponseResult.Exception -> UIState.Exception(response.exception)
        }
    }.asResult(scope)
}

fun ResponseResult<List<Drink>>.mapResponseResultToDrinksUIState(): UIState<List<Drink>> {
    val uiState = when(this){
        is ResponseResult.Success ->
            when(this.data.isNullOrEmpty()){
                true -> UIState.SuccessWithNoData
                false -> UIState.SuccessWithData(this.data)
            }
        is ResponseResult.Error -> UIState.Error(this.code)
        is ResponseResult.Exception -> UIState.Exception(this.exception)
    }

    return uiState
}

fun ResponseResult<Drink>.mapResponseResultToDrinkUIState(): UIState<Drink> {
    return when(this){
        is ResponseResult.Error -> UIState.Error(this.code)
        is ResponseResult.Exception -> UIState.Exception(this.exception)
        is ResponseResult.Success -> this.data?.let {
            UIState.SuccessWithData(it)
        } ?: UIState.SuccessWithNoData
    }
}

private fun <T> Flow<UIState<T>>.asResult(scope: CoroutineScope): StateFlow<UIState<T>> {
    return this
        .onStart {emit(UIState.Loading(true)) }
        .onCompletion {emit(UIState.Loading(false))}
        .catch { emit(UIState.Exception(it)) }
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UIState.Loading(false)
        )
}

suspend fun <T> Flow<UIState<T>>.asFlowWithResult(): Flow<UIState<T>>{
    return this
        .onStart {emit(UIState.Loading(true)) }
        .onCompletion {emit(UIState.Loading(false))}
        .catch { emit(UIState.Exception(it)) }
}