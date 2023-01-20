package com.jumrukovski.cocktailbar.data.model

data class ResponseData<T>(val data: T? = null, val isSuccess: Boolean, val code:Int)
