package com.jumrukovski.cocktailbar.features.display

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.util.Pair
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.jumrukovski.cocktailbar.R
import com.jumrukovski.cocktailbar.base.BaseActivity
import com.jumrukovski.cocktailbar.base.BaseBindingAdapter
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.databinding.ActivityDrinkDetailsBinding
import com.jumrukovski.cocktailbar.ui.state.UIState
import com.jumrukovski.cocktailbar.ui.adapters.IngredientsAdapter
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DrinkDetailsActivity :
    BaseActivity<ActivityDrinkDetailsBinding, DrinkDetailsViewModel>() {

    private val ingredientsAdapter: IngredientsAdapter by inject()

    private val itemClickListener =
        object : BaseBindingAdapter.ItemClickListener<Pair<String, String>> {
            override fun onClick(item: Pair<String, String>, position: Int, sharedViews: Array<View>) {
                item.first?.let {
                    IngredientDetailsActivity.start(this@DrinkDetailsActivity, it)
                }
            }
        }

    private fun collectFavoriteData(isFromClick: Boolean) {
        lifecycleScope.launch {
            viewModel.getFavoriteDrinkFromDB().collect {
                var isFav = it != null
                if (isFromClick) {
                    when (isFav) {
                        true -> viewModel.removeAsFavorite()
                        false -> viewModel.saveAsFavorite()
                    }
                    isFav = !isFav
                }
                manageFavoriteIcon(isFav)
            }
        }
    }

    private fun collectFullDetailsData() {
        lifecycleScope.launch {
            viewModel.getDrinkDetails().collect { uiState ->
                when (uiState) {
                    is UIState.Success -> uiState.data?.let { showData(it) }
                    is UIState.Error -> showErrorMessage("Something went wrong")
                    is UIState.Loading -> ""//todo
                    is UIState.NoData -> ""//todo
                }
            }
        }
    }

    private fun showData(drink: Drink) {
        with(binding) {
            category.text = String.format(
                resources.getString(R.string.drink_type),
                drink.strCategory,
                drink.strAlcoholic
            )
            instructions.text = drink.strInstructions
        }

        ingredientsAdapter.addItems(drink.getIngredientsWithMeasurements()
            .filter { item -> item.first != null })
    }

    override fun init() {
        val drink = intent.getSerializableExtra(EXTRA_ITEM) as Drink
        binding.thumb.load(drink.strDrinkThumb)
        binding.name.text = drink.strDrink
        viewModel.init(drink)
        collectFullDetailsData()
        collectFavoriteData(false)
        makeStatusBarTransparent()
        initAdapter()
        setListeners()
    }

    private fun initAdapter() {
        binding.items.apply {
            layoutManager = LinearLayoutManager(this@DrinkDetailsActivity)
            adapter = ingredientsAdapter
        }
    }

    private fun manageFavoriteIcon(isFav: Boolean) {
        val res = when (isFav) {
            true -> R.drawable.ic_favorite_white_24dp
            false -> R.drawable.ic_saved_white_24dp
        }

        binding.save.setImageDrawable(ResourcesCompat.getDrawable(resources,res,null))
    }

    private fun setListeners() {
        binding.btnBack.setOnClickListener { onBackPressed() }
        binding.instructionsTitle.setOnClickListener { updateInstructionsUI() }
        binding.save.setOnClickListener { collectFavoriteData(true) }
        ingredientsAdapter.setItemClickListener(itemClickListener)
    }

    private fun updateInstructionsUI() {
        with(binding) {
            val result = when (instructions.visibility) {
                View.VISIBLE -> Pair(View.GONE, R.drawable.ic_keyboard_arrow_down_white_24dp)
                else -> Pair(View.VISIBLE, R.drawable.ic_keyboard_arrow_up_white_24dp)
            }

            instructions.visibility = result.first!!
            instructionsTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, result.second!!, 0)
        }
    }

    override fun getLayoutRes(): Int = R.layout.activity_drink_details

    companion object {
        private const val EXTRA_ITEM = "extraItem"

        fun start(context: Activity, item: Drink, imageLayout: View, nameView: View) {
            val starter = Intent(context, DrinkDetailsActivity::class.java)
            starter.putExtra(EXTRA_ITEM, item)

            val sharedViews = arrayOf(Pair(imageLayout, "layout"), Pair(nameView, "name"))
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context, *sharedViews)
            context.startActivity(starter, options.toBundle())
        }
    }

    override val viewModel: DrinkDetailsViewModel by viewModel()
}