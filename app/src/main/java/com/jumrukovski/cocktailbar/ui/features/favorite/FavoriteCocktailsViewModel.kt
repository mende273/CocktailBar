package com.jumrukovski.cocktailbar.ui.features.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.domain.usecase.GetAllFavoriteFromDBUseCase

class FavoriteCocktailsViewModel(getAllFavoriteFromDBUseCase: GetAllFavoriteFromDBUseCase) :
    ViewModel() {

    val allFavorites: LiveData<List<Drink>> = getAllFavoriteFromDBUseCase.getAllFavoriteDrinks()
}
