package com.jumrukovski.cocktailbar.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.jumrukovski.cocktailbar.base.BaseBindingAdapter
import com.jumrukovski.cocktailbar.base.BaseBindingViewHolder
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.databinding.ItemCategoryFilterBinding

class FilterAdapter : BaseBindingAdapter<Drink>() {

    override fun bind(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): ViewDataBinding =
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

    fun removeLastIfNull() {
        val value = items.last().getFilterTitle()

        if (value.isNullOrEmpty()) {
            items.remove(items.last())
            notifyItemRemoved(items.size - 1)
        }
    }
}