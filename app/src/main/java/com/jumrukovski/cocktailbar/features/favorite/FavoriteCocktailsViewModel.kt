package com.jumrukovski.cocktailbar.features.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jumrukovski.cocktailbar.data.domain.GetAllFavoriteFromDBUseCase
import com.jumrukovski.cocktailbar.data.model.Drink

class FavoriteCocktailsViewModel(getAllFavoriteFromDBUseCase: GetAllFavoriteFromDBUseCase) :
    ViewModel() {

    val allFavorites: LiveData<List<Drink>> = getAllFavoriteFromDBUseCase.getAllFavoriteDrinks()
}