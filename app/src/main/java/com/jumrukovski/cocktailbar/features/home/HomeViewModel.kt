package com.jumrukovski.cocktailbar.features.home

import androidx.lifecycle.ViewModel
import com.jumrukovski.cocktailbar.data.domain.GetCocktailsByFirstLetterUseCase
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.network.Resource
import kotlinx.coroutines.flow.Flow

class HomeViewModel(private val getCocktailsByFirstLetter: GetCocktailsByFirstLetterUseCase) :
    ViewModel() {

    val filters = listOf('A'..'Z').flatten()

    fun fetchItems(filterPosition: Int): Flow<Resource<List<Drink>>> {
        val filter = filters[filterPosition].toString()
        return getCocktailsByFirstLetter.loadData(filter)
    }
}