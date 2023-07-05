package com.jumrukovski.cocktailbar.ui.features.display

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.domain.usecase.AddFavoriteDrinkInDBUseCase
import com.jumrukovski.cocktailbar.domain.usecase.GetDrinkDetailsUseCase
import com.jumrukovski.cocktailbar.domain.usecase.GetFavoriteDrinkFromDBUseCase
import com.jumrukovski.cocktailbar.domain.usecase.RemoveFavoriteDrinkFromDBUseCase
import com.jumrukovski.cocktailbar.ui.mapper.mapResponseResultToDrinkUIStateFlow
import com.jumrukovski.cocktailbar.ui.state.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class DrinkDetailsViewModel(
    private val getDrinkDetails: GetDrinkDetailsUseCase,
    private val addFavoriteDrink: AddFavoriteDrinkInDBUseCase,
    private val removeFavoriteDrink: RemoveFavoriteDrinkFromDBUseCase,
    private val getFavoriteDrink: GetFavoriteDrinkFromDBUseCase
) :
    ViewModel() {

    private val _uiState: MutableStateFlow<UIState<Drink>> = MutableStateFlow(UIState.Loading(true))
    val uiState: StateFlow<UIState<Drink>> = _uiState.asStateFlow()

    private lateinit var drink: Drink

    fun init(drink: Drink) {
        this.drink = drink
    }

    fun requestDrinkDetails() {
        viewModelScope.launch {
            drink.idDrink?.let { drinkId ->
                val responseResult = getDrinkDetails(drinkId)
                _uiState.emitAll(responseResult.mapResponseResultToDrinkUIStateFlow())
            }
        }
    }

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
