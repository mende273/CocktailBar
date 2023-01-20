package com.jumrukovski.cocktailbar.features.home

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import androidx.lifecycle.lifecycleScope
import com.jumrukovski.cocktailbar.R
import com.jumrukovski.cocktailbar.base.DisplayDrinksFragment
import com.jumrukovski.cocktailbar.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment :
    DisplayDrinksFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModel()

    private val onFilterClickedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            lifecycleScope.launch {
                collectData(viewModel.fetchItems(position))
            }
        }
    }

    override fun init() {
        initFilterAdapter()
        initItemsAdapter(binding.items)
        setListeners()
    }

    private fun initFilterAdapter() {
        activity?.let {
            binding.filters.adapter =
                ArrayAdapter(it, R.layout.item_spinner_filter, viewModel.filters)
        }
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