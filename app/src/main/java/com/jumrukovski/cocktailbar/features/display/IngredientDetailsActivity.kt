package com.jumrukovski.cocktailbar.features.display

import android.content.Context
import android.content.Intent
import androidx.lifecycle.lifecycleScope
import coil.api.load
import com.jumrukovski.cocktailbar.base.BaseActivity
import com.jumrukovski.cocktailbar.data.model.Ingredient
import com.jumrukovski.cocktailbar.databinding.ActivityIngredientDetailsBinding
import com.jumrukovski.cocktailbar.R
import com.jumrukovski.cocktailbar.network.ApiService
import com.jumrukovski.cocktailbar.network.Resource
import com.jumrukovski.cocktailbar.network.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class IngredientDetailsActivity :
    BaseActivity<ActivityIngredientDetailsBinding, IngredientDetailsViewModel>(
        IngredientDetailsViewModel::class
    ) {

    private fun collectData(data: Flow<Resource<Ingredient>>) {
        lifecycleScope.launch {
            data.collect {
                with(binding) {
                    when (it.status) {
                        Status.LOADING -> showProgress(progress, true)
                        Status.SUCCESS -> {
                            showProgress(progress, false)
                            type.text = String.format(resources.getString(R.string.type), it.data?.strType)
                            alcohol.text = String.format(resources.getString(R.string.alcohol), it.data?.strAlcohol)
                            description.text = it.data?.strDescription
                        }
                        Status.ERROR -> {
                            showProgress(progress, false)
                            showErrorMessage(it.message)
                        }
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
}