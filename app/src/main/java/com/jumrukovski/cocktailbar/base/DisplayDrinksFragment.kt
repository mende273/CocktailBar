package com.jumrukovski.cocktailbar.base

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.features.display.DrinkDetailsActivity
import com.jumrukovski.cocktailbar.network.Resource
import com.jumrukovski.cocktailbar.network.Status
import com.jumrukovski.cocktailbar.ui.adapters.CocktailAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import kotlin.reflect.KClass

abstract class DisplayDrinksFragment<DB : ViewDataBinding, VM : ViewModel>(clazz: KClass<VM>) :
    BaseFragment<DB, VM>(clazz) {

    private val adapter: CocktailAdapter by inject()

    private val itemClickListener = object : BaseBindingAdapter.ItemClickListener<Drink> {
        override fun onClick(item: Drink, p: Int, sViews: Array<View>) {
            DrinkDetailsActivity.start(activity!!, item, sViews[0], sViews[1])
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

    protected fun collectData(data: Flow<Resource<List<Drink>>>) {
        lifecycleScope.launch {
            data.collect {
                displayData(it)
            }
        }
    }

    private fun displayData(data: Resource<List<Drink>>) {
        adapter.clear()
        when (data.status) {
            Status.LOADING -> showProgress(getProgressView(), true)
            Status.SUCCESS -> {
                showProgress(getProgressView(), false)
                adapter.addItems(data.data!!)
            }
            Status.ERROR -> {
                showProgress(getProgressView(), false)
                showErrorMessage(data.message)
            }
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