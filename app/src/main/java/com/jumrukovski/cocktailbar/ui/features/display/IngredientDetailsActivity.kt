package com.jumrukovski.cocktailbar.ui.features.display

import android.content.Context
import android.content.Intent
import androidx.lifecycle.lifecycleScope
import coil.load
import com.jumrukovski.cocktailbar.R
import com.jumrukovski.cocktailbar.base.BaseActivity
import com.jumrukovski.cocktailbar.data.model.Ingredient
import com.jumrukovski.cocktailbar.databinding.ActivityIngredientDetailsBinding
import com.jumrukovski.cocktailbar.ui.state.UIState
import com.jumrukovski.cocktailbar.data.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class IngredientDetailsActivity :
    BaseActivity<ActivityIngredientDetailsBinding, IngredientDetailsViewModel>() {

    private fun collectData(data: Flow<UIState<Ingredient>>) {
        lifecycleScope.launch {
            data.collect { uiState ->
                with(binding) {
                    when (uiState) {
                        is UIState.Loading -> showProgress(progress, uiState.isLoading)
                        is UIState.SuccessWithData -> {
                            type.text = String.format(resources.getString(R.string.type), uiState.data.strType)
                            alcohol.text = String.format(resources.getString(R.string.alcohol), uiState.data.strAlcohol)
                            description.text = uiState.data.strDescription
                        }
                        is UIState.Error -> {
                            //todo showErrorMessage(uiState.code)
                        }
                        is UIState.SuccessWithNoData -> "" //todo
                        is UIState.Exception -> ""//todo
                    }
                }
            }
        }
    }

    override fun init() {
        val name = intent.getStringExtra(EXTRA_INGREDIENT_NAME) ?: ""
        initToolbar(binding.toolbar.toolbar, name)
        loadThumbnail(name)

        lifecycleScope.launch {
            collectData(viewModel.fetchData(name))
        }
    }

    private fun loadThumbnail(name: String) {
        binding.thumb.load("${ApiService.INGREDIENT_IMAGES_ENDPOINT}$name.png")
    }

    override fun getLayoutRes() = R.layout.activity_ingredient_details

    companion object {
        private const val EXTRA_INGREDIENT_NAME = "extraIngredientName"

        fun start(context: Context, name: String) {
            val starter = Intent(context, IngredientDetailsActivity::class.java)
            starter.putExtra(EXTRA_INGREDIENT_NAME, name)
            context.startActivity(starter)
        }
    }

    override val viewModel: IngredientDetailsViewModel by viewModel()
}