package com.jumrukovski.cocktailbar.ui.features.filter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.databinding.ItemCategoryFilterBinding
import com.jumrukovski.cocktailbar.ui.base.BaseBindingAdapter
import com.jumrukovski.cocktailbar.ui.base.BaseBindingViewHolder

class FilterAdapter : BaseBindingAdapter<Drink>() {

    override fun bind(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): ViewBinding =
        ItemCategoryFilterBinding.inflate(inflater, parent, false)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemCategoryFilterBinding.inflate(inflater, parent, false)

        return object : BaseBindingViewHolder(itemBinding, this) {
            override fun sharedViews(): Array<View> {
                return arrayOf(itemBinding.title)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder, position: Int) {
        (holder.binding as ItemCategoryFilterBinding).apply {
            this.title.text = items[position].getFilterTitle()
        }
    }
}
