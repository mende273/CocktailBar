package com.jumrukovski.cocktailbar.network

sealed class Resource<T>(val data: T? = null, val message: String? = null, val status: Status) {
    class Success<T>(data: T) : Resource<T>(data, status = Status.SUCCESS)
    class Loading<T>(data: T? = null) : Resource<T>(data, status = Status.LOADING)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message, Status.ERROR)
}