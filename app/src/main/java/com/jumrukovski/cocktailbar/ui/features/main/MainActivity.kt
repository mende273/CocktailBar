package com.jumrukovski.cocktailbar.ui.features.main

import androidx.viewpager2.widget.ViewPager2
import com.jumrukovski.cocktailbar.R
import com.jumrukovski.cocktailbar.databinding.ActivityMainBinding
import com.jumrukovski.cocktailbar.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun init() {
        initViewPager()
        setListeners()
    }

    private fun initViewPager() {
        binding.viewPager.adapter = FragmentPagerAdapter(this@MainActivity)
    }

    private fun setListeners() {
        binding.bottomNavigation.addBubbleListener { id ->
            val position = when (id) {
                R.id.actionHome -> 0
                R.id.actionFavorite -> 1
                R.id.actionFilter -> 2
                else -> 3
            }
            binding.viewPager.setCurrentItem(position, true)
        }
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                binding.bottomNavigation.setSelected(position)
            }
        })
    }

    override fun getLayoutRes() = R.layout.activity_main

    override val viewModel: MainViewModel by viewModel()

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
}
