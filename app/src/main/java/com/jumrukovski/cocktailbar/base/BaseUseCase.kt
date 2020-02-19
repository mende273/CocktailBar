package com.jumrukovski.cocktailbar.base

import com.jumrukovski.cocktailbar.network.Resource
import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<T> {

    abstract fun loadData(vararg values: String): Flow<Resource<T>>
}