package com.jumrukovski.cocktailbar.ui.features.display

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import coil.load
import com.jumrukovski.cocktailbar.data.model.Drink
import com.jumrukovski.cocktailbar.databinding.ItemDrinkBinding
import com.jumrukovski.cocktailbar.ui.base.BaseBindingAdapter
import com.jumrukovski.cocktailbar.ui.base.BaseBindingViewHolder

class DisplayDrinksAdapter : BaseBindingAdapter<Drink>() {

    override fun bind(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): ViewBinding =
        ItemDrinkBinding.inflate(inflater, parent, false)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemDrinkBinding.inflate(inflater, parent, false)

        return object : BaseBindingViewHolder(itemBinding, this) {
            override fun sharedViews(): Array<View> {
                return arrayOf(itemBinding.layout, itemBinding.title)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder, position: Int) {
        (holder.binding as ItemDrinkBinding).apply {
            val drink = items[position]
            title.text = drink.strDrink
            category.text = drink.strCategory

            thumb.load(drink.strDrinkThumb) {
                crossfade(true)
            }
        }
    }

    fun setData(newItems: List<Drink>) {
        val diffCallback = DrinkDiffCallback(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    class DrinkDiffCallback(private val oldList: List<Drink>, private val newList: List<Drink>) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].idDrink === newList[newItemPosition].idDrink
        }

        override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            val (_, _, name) = oldList[oldPosition]
            val (_, _, name1) = newList[newPosition]

            return name == name1
        }

        @Nullable
        override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
            return super.getChangePayload(oldPosition, newPosition)
        }
    }
}
