package com.jumrukovski.cocktailbar.ui.features.filter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jumrukovski.cocktailbar.R
import com.jumrukovski.cocktailbar.data.model.Filter
import com.jumrukovski.cocktailbar.databinding.ActivityFilteredDrinksBinding

class FilteredDrinksActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilteredDrinksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilteredDrinksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val filter = intent.getSerializableExtra(EXTRA_FILTER) as Filter
        initToolbar(filter)
        setFragment(filter)
    }

    private fun setFragment(filter: Filter) {
        val fragment = FilteredDrinksFragment.newInstance(filter)
        supportFragmentManager.beginTransaction().add(R.id.container, fragment, "filter").commit()
    }

    private fun initToolbar(filter: Filter) {
        setSupportActionBar(binding.toolbar.toolbar)

        supportActionBar?.apply {
            title = "${filter.filterBy} : ${filter.value}"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    companion object {
        private const val EXTRA_FILTER = "extraFilter"

        fun start(context: Context, filter: Filter) {
            val starter = Intent(context, FilteredDrinksActivity::class.java)
            starter.putExtra(EXTRA_FILTER, filter)
            context.startActivity(starter)
        }
    }
}
