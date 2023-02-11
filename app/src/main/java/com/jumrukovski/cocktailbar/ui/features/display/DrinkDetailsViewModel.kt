package com.jumrukovski.cocktailbar.ui.features.display

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.cocktailbar.domain.usecase.AddFavoriteDrinkInDBUseCase
import com.jumrukovski.cocktailbar.domain.usecase.GetDrinkDetailsUseCase
import com.jumrukovski.cocktailbar.domain.usecase.GetFavoriteDrinkFromDBUseCase
import com.jumrukovski.cocktailbar.domain.usecase.RemoveFavoriteDrinkFromDBUseCase
import com.jumrukovski.cocktailbar.domain.mapper.mapAsDrinkUIState
import com.jumrukovski.cocktailbar.data.model.Drink
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class DrinkDetailsViewModel(
    private val getDrinkDetails: GetDrinkDetailsUseCase,
    private val addFavoriteDrink: AddFavoriteDrinkInDBUseCase,
    private val removeFavoriteDrink: RemoveFavoriteDrinkFromDBUseCase,
    private val getFavoriteDrink: GetFavoriteDrinkFromDBUseCase
) :
    ViewModel() {

    private lateinit var drink: Drink

    fun init(drink: Drink) {
        this.drink = drink
    }

    fun getDrinkDetails() = drink.idDrink?.let { getDrinkDetails.invoke(it).mapAsDrinkUIState(viewModelScope) }

    fun getFavoriteDrinkFromDB() =
        getFavoriteDrink.getFavoriteDrink(drink.idDrink!!).flowOn(Dispatchers.IO)

    fun saveAsFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            addFavoriteDrink.addFavorite(drink)
        }
    }

    fun removeAsFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            removeFavoriteDrink.removeFavorite(drink)
        }
    }
}