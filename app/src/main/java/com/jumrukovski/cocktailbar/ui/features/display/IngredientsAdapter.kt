package com.jumrukovski.cocktailbar.ui.features.display

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.databinding.ViewDataBinding
import com.jumrukovski.cocktailbar.base.BaseBindingAdapter
import com.jumrukovski.cocktailbar.base.BaseBindingViewHolder
import com.jumrukovski.cocktailbar.databinding.ItemIngredientBinding

class IngredientsAdapter : BaseBindingAdapter<Pair<String, String>>() {

    override fun bind(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): ViewDataBinding =
        ItemIngredientBinding.inflate(inflater, parent, false)

    override fun onBindViewHolder(holder: BaseBindingViewHolder, position: Int) {
        (holder.binding as ItemIngredientBinding).apply {
            with(items[position]) {
                ingredient.text = first
                measure.text = second
            }
        }
    }
}