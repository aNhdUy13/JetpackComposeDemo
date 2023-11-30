package com.mvpvn.jetpackcomposedemo.core.extension

import android.graphics.Color
import android.os.Build
import androidx.core.view.WindowInsetsControllerCompat
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.mvpvn.jetpackcomposedemo.R

fun Window.makeFullScreen() {
    WindowCompat.setDecorFitsSystemWindows(this, false)
    decorView.setOnApplyWindowInsetsListener { _, insets ->
        insets
    }
}

fun Window.makeFullScreenAndHideSystemBar() {
    WindowCompat.setDecorFitsSystemWindows(this, false)

    val controller = WindowInsetsControllerCompat(this, decorView).apply {
        systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        hide(WindowInsetsCompat.Type.systemBars())
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        isNavigationBarContrastEnforced = false
        isStatusBarContrastEnforced = false
        navigationBarColor = Color.TRANSPARENT
        statusBarColor = Color.TRANSPARENT
    } else {
        statusBarColor = Color.TRANSPARENT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            navigationBarColor = Color.TRANSPARENT
        }
    }
}

fun Window.changeSystemBarColor(activity: ComponentActivity) {
    statusBarColor = ContextCompat.getColor(activity, R.color.black)
    navigationBarColor = ContextCompat.getColor(activity, R.color.black)
}

