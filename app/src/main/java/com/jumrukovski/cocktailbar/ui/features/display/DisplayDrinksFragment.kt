package com.jumrukovski.cocktailbar.ui.features.display

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jumrukovski.cocktailbar.base.BaseBindingAdapter
import com.jumrukovski.cocktailbar.base.BaseFragment
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.ui.state.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

abstract class DisplayDrinksFragment<DB : ViewDataBinding, VM : ViewModel> :
    BaseFragment<DB, VM>() {

    private val adapter: DisplayDrinksAdapter by inject()

    private val itemClickListener = object : BaseBindingAdapter.ItemClickListener<Drink> {
        override fun onClick(item: Drink, position: Int, sharedViews: Array<View>) {
            activity?.let {
                DrinkDetailsActivity.start(it, item, sharedViews[0], sharedViews[1])
            }
        }
    }

    protected val itemsLiveDataObserver = Observer<List<Drink>> {
        if (it.isNullOrEmpty()) {
            adapter.clear()
        } else {
            adapter.setData(it)
        }
    }

    abstract fun getProgressView(): ProgressBar

    protected fun collectData(data: Flow<UIState<List<Drink>>>) {
        lifecycleScope.launch {
            data.collect {
                displayData(it)
            }
        }
    }

    protected fun displayData(uiState: UIState<List<Drink>>) {
        when (uiState) {
            is UIState.Loading -> showProgress(getProgressView(), uiState.isLoading)
            is UIState.SuccessWithData -> {
               adapter.apply {
                   clear()
                   addItems(uiState.data)
               }
            }
            is UIState.Error -> "" // todo
            is UIState.SuccessWithNoData -> "" //todo
            is UIState.Exception -> "" //todo
        }
    }

    protected fun initItemsAdapter(recyclerView: RecyclerView) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            this.adapter = this@DisplayDrinksFragment.adapter
        }
        adapter.setItemClickListener(itemClickListener)
    }
}