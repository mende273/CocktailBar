package com.jumrukovski.cocktailbar.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {

    protected lateinit var binding: VB

    protected abstract val viewModel: VM

    abstract fun getViewBinding(): VB

    @LayoutRes
    abstract fun getLayoutRes(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    abstract fun init()

    protected fun showErrorMessage(message: Int) {
        Toast.makeText(activity, activity?.resources?.getString(message), Toast.LENGTH_SHORT).show()
    }

    protected fun showErrorMessage(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    protected fun showProgress(progress: ProgressBar, shouldShow: Boolean) {
        progress.visibility = when (shouldShow) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }
}
