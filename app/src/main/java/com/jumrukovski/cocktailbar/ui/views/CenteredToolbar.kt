package com.jumrukovski.cocktailbar.ui.views

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.jumrukovski.cocktailbar.R

class CenteredToolbar(context: Context?, attrs: AttributeSet?) : Toolbar(context!!, attrs) {

    private var centeredTitleTextView: TextView? = null

    override fun setTitle(@StringRes resId: Int) {
        val s = resources.getString(resId)
        title = s
    }

    override fun setTitle(title: CharSequence) {
        getCenteredTitleTextView().text = title
    }

    override fun getTitle(): CharSequence {
        return getCenteredTitleTextView().text.toString()
    }

    private fun getCenteredTitleTextView(): TextView {
        if (centeredTitleTextView == null) {
            centeredTitleTextView = TextView(context)
            centeredTitleTextView?.apply {
                setSingleLine()
                ellipsize = TextUtils.TruncateAt.END
                gravity = Gravity.CENTER
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
                setTextColor(ContextCompat.getColor(context, R.color.white))
                setTextAppearance(R.style.TextAppearance_AppCompat_Widget_ActionBar_Title)

                val lp = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                lp.gravity = Gravity.CENTER
                layoutParams = lp
            }

            addView(centeredTitleTextView)
        }
        return centeredTitleTextView!!
    }
}
