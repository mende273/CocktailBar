package com.jumrukovski.cocktailbar.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class BaseBindingViewHolder(val binding: ViewBinding, private val clickListener: ClickListener) :
    RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    interface ClickListener {
        fun onViewClick(position: Int, sharedViews: Array<View>)
    }

    open fun sharedViews(): Array<View> = arrayOf()

    init {
        binding.root.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        clickListener.onViewClick(adapterPosition, sharedViews())
    }
}
