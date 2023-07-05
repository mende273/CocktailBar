package com.jumrukovski.cocktailbar.ui.state

sealed interface UIState<out T> {
    data class SuccessWithData<T>(val data: T) : UIState<T>
    data class Loading(val isLoading: Boolean) : UIState<Nothing>
    data class Error(val code: Int) : UIState<Nothing>
    data class Exception(val exception: Throwable?) : UIState<Nothing>
    object SuccessWithNoData : UIState<Nothing>
}
