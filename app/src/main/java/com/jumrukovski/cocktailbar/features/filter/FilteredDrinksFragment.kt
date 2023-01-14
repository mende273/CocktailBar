package com.jumrukovski.cocktailbar.features.filter

import android.os.Bundle
import android.widget.ProgressBar
import com.jumrukovski.cocktailbar.R
import com.jumrukovski.cocktailbar.base.DisplayDrinksFragment
import com.jumrukovski.cocktailbar.data.model.Filter
import com.jumrukovski.cocktailbar.databinding.FragmentFilteredDrinksBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilteredDrinksFragment :
    DisplayDrinksFragment<FragmentFilteredDrinksBinding, FilteredDrinksViewModel>() {

    override fun init() {
        initItemsAdapter(binding.items)
        val filter = arguments?.getSerializable(EXTRA_FILTER) as Filter
        collectData(viewModel.fetchItems(filter))
    }

    override fun getProgressView(): ProgressBar = binding.progress

    override fun getLayoutRes(): Int = R.layout.fragment_filtered_drinks

    companion object {
        private const val EXTRA_FILTER = "extraFilter"

        fun newInstance(filter: Filter): FilteredDrinksFragment {
            val fragment = FilteredDrinksFragment()
            fragment.arguments = Bundle().apply {
                putSerializable(EXTRA_FILTER, filter)
            }
            return fragment
        }
    }

    override val viewModel: FilteredDrinksViewModel by viewModel()
}