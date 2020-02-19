package com.jumrukovski.cocktailbar.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseFragment<DB : ViewDataBinding, VM : ViewModel>(clazz: KClass<VM>) : Fragment() {

    protected lateinit var binding: DB

    protected val viewModel: VM by viewModel(clazz)

    @LayoutRes
    abstract fun getLayoutRes(): Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
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