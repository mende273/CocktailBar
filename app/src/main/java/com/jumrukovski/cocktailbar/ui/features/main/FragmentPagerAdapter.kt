package com.jumrukovski.cocktailbar.ui.features.main

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jumrukovski.cocktailbar.ui.features.favorite.FavoriteCocktailsFragment
import com.jumrukovski.cocktailbar.ui.features.filter.FilterFragment
import com.jumrukovski.cocktailbar.ui.features.home.HomeFragment
import com.jumrukovski.cocktailbar.ui.features.search.SearchFragment

class FragmentPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = 4

    override fun createFragment(position: Int) = when (position) {
        0 -> HomeFragment.newInstance()
        1 -> FavoriteCocktailsFragment.newInstance()
        2 -> FilterFragment.newInstance()
        else -> SearchFragment.newInstance()
    }
}
