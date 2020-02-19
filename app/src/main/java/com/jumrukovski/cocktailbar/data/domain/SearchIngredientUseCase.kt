package com.jumrukovski.cocktailbar.data.domain

import com.jumrukovski.cocktailbar.base.BaseUseCase
import com.jumrukovski.cocktailbar.data.model.Ingredient
import com.jumrukovski.cocktailbar.data.repository.ApiRepository
import com.jumrukovski.cocktailbar.network.Resource
import kotlinx.coroutines.flow.flow

class SearchIngredientUseCase(private val apiRepository: ApiRepository) :
    BaseUseCase<Ingredient>() {

    override fun loadData(vararg values: String) = flow {
        emit(Resource.Loading())
        val response = apiRepository.searchIngredient(values[0])
        val resource: Resource<Ingredient> = when (response.isSuccessful) {
            true -> Resource.Success(response.body()!!.ingredients[0])
            false -> Resource.Error(response.message())
        }
        emit(resource)
    }
}