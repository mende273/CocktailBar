package com.jumrukovski.cocktailbar.features.main

import androidx.viewpager2.widget.ViewPager2
import com.jumrukovski.cocktailbar.R
import com.jumrukovski.cocktailbar.base.BaseActivity
import com.jumrukovski.cocktailbar.databinding.ActivityMainBinding
import com.jumrukovski.cocktailbar.ui.adapters.FragmentPagerAdapter
import com.fxn.OnBubbleClickListener

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    MainViewModel::class
) {

    override fun init() {
        initViewPager()
        setListeners()
    }

    private fun initViewPager() {
        binding.viewPager.adapter = FragmentPagerAdapter(this@MainActivity)
    }

    private fun setListeners() {
        binding.bottomNavigation.addBubbleListener(object : OnBubbleClickListener {
            override fun onBubbleClick(id: Int) {
                val position = when (id) {
                    R.id.actionHome -> 0
                    R.id.actionFavorite -> 1
                    R.id.actionFilter -> 2
                    else -> 3
                }
                binding.viewPager.setCurrentItem(position, true)
            }
        })
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                binding.bottomNavigation.setSelected(position)
            }
        })
    }

    override fun getLayoutRes() = R.layout.activity_main
}