package com.jumrukovski.cocktailbar.ui.state

sealed interface UIState<out T> {
    data class Success<T>(val data: T?) : UIState<T>
    data class Loading(val isLoading: Boolean) : UIState<Nothing>
    data class Error(val code: Int) : UIState<Nothing>
    object NoData : UIState<Nothing>
}