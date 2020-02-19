package com.jumrukovski.cocktailbar.features.home

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import com.jumrukovski.cocktailbar.R
import com.jumrukovski.cocktailbar.base.DisplayDrinksFragment
import com.jumrukovski.cocktailbar.databinding.FragmentHomeBinding

class HomeFragment :
    DisplayDrinksFragment<FragmentHomeBinding, HomeViewModel>(HomeViewModel::class) {

    private val onFilterClickedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            collectData(viewModel.fetchItems(position))
        }
    }

    override fun init() {
        initFilterAdapter()
        initItemsAdapter(binding.items)
        setListeners()
    }

    private fun initFilterAdapter() {
        binding.filters.adapter =
            ArrayAdapter(activity!!, R.layout.item_spinner_filter, viewModel.filters)
    }

    private fun setListeners() {
        binding.filters.onItemSelectedListener = onFilterClickedListener
    }

    override fun getLayoutRes() = R.layout.fragment_home

    override fun getProgressView(): ProgressBar = binding.progress

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}