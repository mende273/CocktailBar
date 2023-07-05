package com.jumrukovski.cocktailbar.ui.features.search

import android.content.Context
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.jumrukovski.cocktailbar.R
import com.jumrukovski.cocktailbar.databinding.FragmentSearchBinding
import com.jumrukovski.cocktailbar.ui.features.display.DisplayDrinksFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment :
    DisplayDrinksFragment<FragmentSearchBinding, SearchViewModel>() {

    override fun init() {
        initItemsAdapter(binding.items)
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

    private fun setListeners() {
        binding.search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard()
                loadData()
            }
            true
        }
    }

    private fun hideKeyboard() {
        binding.search.clearFocus()
        val im = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        im.hideSoftInputFromWindow(binding.search.windowToken, 0)
    }

    private fun loadData() {
        val text = binding.search.text.toString().trim()
        if (text.isEmpty()) {
            showErrorMessage(R.string.error_search_field_empty)
            return
        }

        viewModel.requestData(text)
    }

    override fun getLayoutRes() = R.layout.fragment_search

    override fun getProgressView(): ProgressBar = binding.progress

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }

    override val viewModel: SearchViewModel by viewModel()

    override fun getViewBinding() = FragmentSearchBinding.inflate(layoutInflater)
}
