package com.jumrukovski.cocktailbar.ui.features.display

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.domain.usecase.AddFavoriteDrinkInDBUseCase
import com.jumrukovski.cocktailbar.domain.usecase.GetDrinkDetailsUseCase
import com.jumrukovski.cocktailbar.domain.usecase.GetFavoriteDrinkFromDBUseCase
import com.jumrukovski.cocktailbar.domain.usecase.RemoveFavoriteDrinkFromDBUseCase
import com.jumrukovski.cocktailbar.ui.mapper.mapResultToDrinkUIStateFlow
import com.jumrukovski.cocktailbar.ui.state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DrinkDetailsViewModel(
    private val getDrinkDetailsUseCase: GetDrinkDetailsUseCase,
    private val addFavoriteDrinkInDBUseCase: AddFavoriteDrinkInDBUseCase,
    private val removeFavoriteDrinkFromDBUseCase: RemoveFavoriteDrinkFromDBUseCase,
    private val getFavoriteDrinkFromDBUseCase: GetFavoriteDrinkFromDBUseCase
) :
    ViewModel() {

    private lateinit var drink: Drink

    private val _uiState: MutableStateFlow<UIState<Drink>> = MutableStateFlow(UIState.Loading(true))

    private val _isFavoriteDrink = MutableStateFlow(false)

    val combinedResult: StateFlow<Pair<UIState<Drink>?, Boolean>> =
        combine(_uiState, _isFavoriteDrink) { state, isFav ->
            Pair(state, isFav)
        }.stateIn(
            scope = viewModelScope,
            initialValue = Pair(UIState.Loading(true), false),
            started = SharingStarted.WhileSubscribed(5_000)
        )

    fun init(drink: Drink) {
        this.drink = drink
        drink.idDrink?.let { requestDrinkDetails(it) }
        drink.idDrink?.let { checkIsFavoriteDrinkFromDB(it) }
    }

    private fun requestDrinkDetails(drinkId: Long) {
        viewModelScope.launch {
            drinkId.let { id ->
                val responseResult = getDrinkDetailsUseCase(id)
                responseResult.mapResultToDrinkUIStateFlow().collectLatest { data ->
                    _uiState.update { data }
                }
            }
        }
    }

    private fun checkIsFavoriteDrinkFromDB(drinkId: Long) {
        viewModelScope.launch {
            val drink = getFavoriteDrinkFromDBUseCase(drinkId)
            _isFavoriteDrink.value = drink != null
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            if (_isFavoriteDrink.value) {
                removeFavoriteDrinkFromDBUseCase(drink)
            } else {
                addFavoriteDrinkInDBUseCase(drink)
            }

            drink.idDrink?.let { checkIsFavoriteDrinkFromDB(it) }
        }
    }
}
