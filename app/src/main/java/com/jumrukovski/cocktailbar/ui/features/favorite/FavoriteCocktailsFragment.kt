package com.jumrukovski.cocktailbar.ui.features.favorite

import android.widget.ProgressBar
import com.jumrukovski.cocktailbar.R
import com.jumrukovski.cocktailbar.databinding.FragmentFavoriteBinding
import com.jumrukovski.cocktailbar.ui.features.display.DisplayDrinksFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteCocktailsFragment :
    DisplayDrinksFragment<FragmentFavoriteBinding, FavoriteCocktailsViewModel>() {

    override fun init() {
        initItemsAdapter(binding.items)
        setListeners()
    }

    private fun setListeners() {
        viewModel.allFavorites.observe(viewLifecycleOwner, itemsLiveDataObserver)
    }

    override fun getProgressView(): ProgressBar = binding.progress

    override fun getLayoutRes() = R.layout.fragment_favorite

    companion object {
        fun newInstance(): FavoriteCocktailsFragment {
            return FavoriteCocktailsFragment()
        }
    }

    override val viewModel: FavoriteCocktailsViewModel by viewModel()

    override fun getViewBinding() = FragmentFavoriteBinding.inflate(layoutInflater)
}
