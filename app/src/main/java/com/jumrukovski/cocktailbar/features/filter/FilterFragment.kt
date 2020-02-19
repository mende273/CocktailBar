package com.jumrukovski.cocktailbar.features.filter

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jumrukovski.cocktailbar.R
import com.jumrukovski.cocktailbar.base.BaseBindingAdapter
import com.jumrukovski.cocktailbar.base.BaseFragment
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.databinding.FragmentFilterBinding
import com.jumrukovski.cocktailbar.network.Resource
import com.jumrukovski.cocktailbar.network.Status
import com.jumrukovski.cocktailbar.ui.adapters.FilterAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class FilterFragment :
    BaseFragment<FragmentFilterBinding, FilterViewModel>(FilterViewModel::class) {

    private val filterAdapter: FilterAdapter by inject()

    private val onDrinkClickedListener = object : BaseBindingAdapter.ItemClickListener<Drink> {
        override fun onClick(item: Drink, position: Int, sharedViews: Array<View>) {
            FilteredDrinksActivity.start(activity!!, viewModel.createFilter(item.getFilterTitle()))
        }
    }

    private val onFilterClickedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            viewModel.setCurrentFilter((view as TextView).text.toString())
            viewModel.fetchData()
            collectData(viewModel.fetchData())
        }
    }

    private fun collectData(data: Flow<Resource<List<Drink>>>) {
        filterAdapter.clear()
        lifecycleScope.launch {
            data.collect {
                when (it.status) {
                    Status.LOADING -> showProgress(binding.progress, true)
                    Status.SUCCESS -> {
                        showProgress(binding.progress, false)
                        filterAdapter.apply {
                            addItems(it.data!!)
                            removeLastIfNull()
                        }
                    }
                    Status.ERROR -> {
                        showProgress(binding.progress, false)
                        showErrorMessage(it.message)
                    }
                }
            }
        }
    }

    override fun init() {
        initFiltersAdapter()
        initItemsAdapter()
        setListeners()
    }

    private fun initItemsAdapter() {
        binding.items.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = filterAdapter
        }
    }

    private fun initFiltersAdapter() {
        binding.filters.adapter =
            ArrayAdapter(activity!!, R.layout.item_spinner_filter, viewModel.getSupportedFilters())
    }

    private fun setListeners() {
        binding.filters.onItemSelectedListener = onFilterClickedListener
        filterAdapter.setItemClickListener(onDrinkClickedListener)
    }

    override fun getLayoutRes() = R.layout.fragment_filter

    companion object {
        fun newInstance(): FilterFragment {
            return FilterFragment()
        }
    }
}