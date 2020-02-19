package com.jumrukovski.cocktailbar.base

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseBindingViewHolder(val binding: ViewDataBinding, private val clickListener: ClickListener) :
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