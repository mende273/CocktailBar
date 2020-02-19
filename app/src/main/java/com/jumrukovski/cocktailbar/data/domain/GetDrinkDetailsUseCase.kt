package com.jumrukovski.cocktailbar.data.domain

import com.jumrukovski.cocktailbar.base.BaseUseCase
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.data.repository.ApiRepository
import com.jumrukovski.cocktailbar.network.Resource
import kotlinx.coroutines.flow.flow

class GetDrinkDetailsUseCase(private val apiRepository: ApiRepository) : BaseUseCase<Drink>() {

    override fun loadData(vararg values: String) = flow {
        emit(Resource.Loading())
        val response = apiRepository.getDrinkDetails(values[0].toLong())
        val resource: Resource<Drink> = when (response.isSuccessful) {
            true -> Resource.Success(response.body()?.drinks!![0])
            false -> Resource.Error(response.message())
        }

        emit(resource)
    }
}