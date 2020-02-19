package com.jumrukovski.cocktailbar.data.domain

import com.jumrukovski.cocktailbar.base.BaseUseCase
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.data.repository.ApiRepository
import com.jumrukovski.cocktailbar.network.Resource
import kotlinx.coroutines.flow.flow

class GetFilteredDrinksUseCase(private val apiRepository: ApiRepository) :
    BaseUseCase<List<Drink>>() {

    override fun loadData(vararg values: String) = flow {
        emit(Resource.Loading())
        val response = apiRepository.getFilteredDrinks(values[0], values[1])
        val resource: Resource<List<Drink>> = when (response.isSuccessful) {
            true -> Resource.Success(response.body()?.drinks ?: listOf())
            false -> Resource.Error(response.message())
        }

        emit(resource)
    }
}