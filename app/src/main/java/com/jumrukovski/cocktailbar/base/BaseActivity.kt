package com.jumrukovski.cocktailbar.base

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.jumrukovski.cocktailbar.ui.views.CenteredToolbar
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass


abstract class BaseActivity<DB : ViewDataBinding, VM : ViewModel>(clazz: KClass<VM>) :
    AppCompatActivity() {

    protected lateinit var binding: DB

    protected val viewModel: VM by viewModel(clazz)

    @LayoutRes
    abstract fun getLayoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutRes())

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
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }

    protected fun showErrorMessage(message: String?) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    protected fun showProgress(progress: ProgressBar, shouldShow: Boolean) {
        progress.visibility = when (shouldShow) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }
}