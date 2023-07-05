package com.jumrukovski.cocktailbar.ui.features.filter

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jumrukovski.cocktailbar.R
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.databinding.FragmentFilterBinding
import com.jumrukovski.cocktailbar.ui.base.BaseBindingAdapter
import com.jumrukovski.cocktailbar.ui.base.BaseFragment
import com.jumrukovski.cocktailbar.ui.state.UIState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilterFragment :
    BaseFragment<FragmentFilterBinding, FilterViewModel>() {

    private val filterAdapter: FilterAdapter by inject()

    private val onDrinkClickedListener = object : BaseBindingAdapter.ItemClickListener<Drink> {
        override fun onClick(item: Drink, position: Int, sharedViews: Array<View>) {
            activity?.let {
                FilteredDrinksActivity.start(it, viewModel.createFilter(item.getFilterTitle()))
            }
        }
    }

    private val onFilterClickedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            viewModel.requestData((view as TextView).text.toString())
        }
    }

    private fun displayData(uiState: UIState<List<Drink>>) {
        when (uiState) {
            is UIState.Loading -> showProgress(binding.progress, uiState.isLoading)
            is UIState.SuccessWithData -> {
                filterAdapter.apply {
                    clear()
                    addItems(uiState.data)
                }
            }
            is UIState.Error -> "" // todo
            is UIState.SuccessWithNoData -> filterAdapter.clear()
            is UIState.Exception -> "" // todo
        }
    }

    override fun init() {
        initFiltersAdapter()
        initItemsAdapter()
        setListeners()
        setObservers()
    }

    private fun setObservers() {
        lifecycleScope.launch {
            viewModel.uiState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest {
                    displayData(it)
                }
        }
    }

    private fun initItemsAdapter() {
        binding.items.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = filterAdapter
        }
    }

    private fun initFiltersAdapter() {
        binding.filters.adapter =
            ArrayAdapter(
                requireActivity(),
                R.layout.item_spinner_filter,
                viewModel.getSupportedFilters()
            )
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

    override val viewModel: FilterViewModel by viewModel()

    override fun getViewBinding() = FragmentFilterBinding.inflate(layoutInflater)
}
