package com.example.news_eat_fronted.util

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.SnackbarCustomBinding
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_FADE
import com.google.android.material.snackbar.Snackbar

class CustomSnackBar(view: View, private val message: String) {
    companion object {
        fun make(view: View, message: String) = CustomSnackBar(view, message)
    }

    private val context = view.context
    private val snackBar = Snackbar.make(view, "", 2000)
    @SuppressLint("RestrictedApi")
    private val snackBarView = snackBar.view as Snackbar.SnackbarLayout
    private val inflater = LayoutInflater.from(context)
    private val snackBarBinding: SnackbarCustomBinding = DataBindingUtil.inflate(inflater, R.layout.snackbar_custom, null, false)

    @SuppressLint("RestrictedApi")
    private fun initLayout() {
        with(snackBarView) {
            removeAllViews()

            val params = layoutParams as ViewGroup.MarginLayoutParams
            params.bottomMargin = 60.dpToPx(context)
            layoutParams = params

            setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            addView(snackBarBinding.root, 0)
        }
    }

    private fun initData() {
        snackBarBinding.snackbarText.text = message
    }

    fun show() {
        snackBar.animationMode = ANIMATION_MODE_FADE
        snackBar.show()
    }

    private fun Int.dpToPx(context: Context): Int =
        (this * context.resources.displayMetrics.density).toInt()

    init {
        initLayout()
        initData()
    }
}