package com.jumrukovski.cocktailbar.features.display

import android.content.Context
import android.content.Intent
import androidx.lifecycle.lifecycleScope
import coil.api.load
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
                        is UIState.Success -> {
                            showProgress(progress, false)
                            uiState.data?.let { data ->
                                type.text = String.format(resources.getString(R.string.type), data.strType)
                                alcohol.text = String.format(resources.getString(R.string.alcohol), data.strAlcohol)
                                description.text = data.strDescription
                            }
                        }
                        is UIState.Error -> {
                            showProgress(progress, false)
                            //todo showErrorMessage(uiState.code)
                        }
                        is UIState.NoData -> "" //todo
                    }
                }
            }
        }
    }

    override fun init() {
        val name = intent.getStringExtra(EXTRA_INGREDIENT_NAME) ?: ""
        initToolbar(binding.toolbar.toolbar, name)
        loadThumbnail(name)
        collectData(viewModel.fetchData(name))
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