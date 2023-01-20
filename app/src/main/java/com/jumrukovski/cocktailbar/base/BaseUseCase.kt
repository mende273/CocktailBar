package com.jumrukovski.cocktailbar.base

import com.jumrukovski.cocktailbar.data.model.ResponseData
import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<T> {

    abstract fun invoke(vararg values: String): Flow<ResponseData<T>>
}