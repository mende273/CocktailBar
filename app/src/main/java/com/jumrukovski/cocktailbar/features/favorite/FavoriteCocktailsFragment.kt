package com.jumrukovski.cocktailbar.features.favorite

import android.widget.ProgressBar
import com.jumrukovski.cocktailbar.R
import com.jumrukovski.cocktailbar.base.DisplayDrinksFragment
import com.jumrukovski.cocktailbar.databinding.FragmentFavoriteBinding
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
}