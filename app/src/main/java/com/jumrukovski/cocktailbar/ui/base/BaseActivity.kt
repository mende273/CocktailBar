package com.jumrukovski.cocktailbar.ui.base

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.jumrukovski.cocktailbar.ui.views.CenteredToolbar

abstract class BaseActivity<VB : ViewBinding, VM : ViewModel> : AppCompatActivity() {

    protected lateinit var binding: VB

    protected abstract val viewModel: VM

    abstract fun getViewBinding(): VB

    @LayoutRes
    abstract fun getLayoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)

        init()
    }

    abstract fun init()

    protected fun initToolbar(toolbar: CenteredToolbar, toolbarTitle: String) {
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            title = toolbarTitle
            setDisplayHomeAsUpEnabled(true)
        }
    }

    protected fun makeStatusBarTransparent() {
        window.statusBarColor = Color.TRANSPARENT
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    protected fun showErrorMessage(message: String?) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    protected fun showProgress(progress: ProgressBar, shouldShow: Boolean) {
        progress.visibility = when (shouldShow) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }
}
