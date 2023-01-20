package com.jumrukovski.cocktailbar.domain.mapper

import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.data.model.Ingredient
import com.jumrukovski.cocktailbar.data.model.ResponseData
import com.jumrukovski.cocktailbar.ui.state.UIState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

fun Flow<ResponseData<List<Drink>>>.asDrinksUIState(scope: CoroutineScope): StateFlow<UIState<List<Drink>>> {
    return this.map { response ->
        when (response.isSuccess) {
            true -> if (response.data.isNullOrEmpty()) {
                UIState.NoData
            } else {
                UIState.Success(response.data)
            }
            false -> UIState.Error(response.code)
        }
    }
        .onStart { UIState.Loading(true) }
        .onCompletion { UIState.Loading(false) }
        .catch { emit(UIState.Error(-1)) }
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UIState.Loading(false)
        )
}


fun Flow<ResponseData<Drink>>.asDrinkUIState(scope: CoroutineScope): StateFlow<UIState<Drink>> {
    return this.map { response ->
        when (response.isSuccess) {
            true -> {
                response.data?.let {
                    UIState.Success(it)
                } ?: UIState.NoData
            }
            false -> UIState.Error(response.code)
        }
    }.onStart { UIState.Loading(true) }
        .onCompletion { UIState.Loading(false) }
        .catch { emit(UIState.Error(-1)) }
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UIState.Loading(false)
        )
}

fun Flow<ResponseData<Ingredient>>.asIngredientUIState(scope: CoroutineScope): StateFlow<UIState<Ingredient>> {
    return this.map { response ->
        when (response.isSuccess) {
            true -> {
                response.data?.let {
                    UIState.Success(response.data)
                } ?: UIState.NoData
            }
            false -> UIState.Error(response.code)
        }
    }.onStart { UIState.Loading(true) }
        .onCompletion { UIState.Loading(false) }
        .catch { emit(UIState.Error(-1)) }
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UIState.Loading(false)
        )
}