package com.jumrukovski.cocktailbar.ui.features.display

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.viewbinding.ViewBinding
import com.jumrukovski.cocktailbar.databinding.ItemIngredientBinding
import com.jumrukovski.cocktailbar.ui.base.BaseBindingAdapter
import com.jumrukovski.cocktailbar.ui.base.BaseBindingViewHolder

class IngredientsAdapter : BaseBindingAdapter<Pair<String, String>>() {

    override fun bind(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): ViewBinding =
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
