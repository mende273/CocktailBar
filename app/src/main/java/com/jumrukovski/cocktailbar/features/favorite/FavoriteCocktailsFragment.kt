package com.jumrukovski.cocktailbar.features.favorite

import android.widget.ProgressBar
import com.jumrukovski.cocktailbar.R
import com.jumrukovski.cocktailbar.base.DisplayDrinksFragment
import com.jumrukovski.cocktailbar.databinding.FragmentFavoriteBinding

class FavoriteCocktailsFragment :
    DisplayDrinksFragment<FragmentFavoriteBinding, FavoriteCocktailsViewModel>(
        FavoriteCocktailsViewModel::class
    ) {

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
}