package com.example.news_eat_fronted.util

import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun View.setupKeyboardHideOnTouch() {
    this.setOnTouchListener { _, event ->
        if (event.action == MotionEvent.ACTION_DOWN) {
            hideKeyboard()
        }
        false
    }
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
    clearFocus()
}

fun Activity.setupKeyboardHide() {
    this.window.decorView.rootView.setupKeyboardHideOnTouch()
}

fun Fragment.setupKeyboardHide() {
    view?.setupKeyboardHideOnTouch()
}